package com.clone.semo.domain.monitoring.service;

import com.clone.semo.domain.monitoring.dto.SessionStatusDto;
import com.clone.semo.domain.targetdb.entity.DbConfig;
import com.clone.semo.domain.targetdb.repository.DbConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MonitoringService {
    private final DbConfigRepository dbConfigRepository;
    private final Map<Long, SessionStatusDto> statusCache = new ConcurrentHashMap<>();

    @Transactional(readOnly = true) // 수집은 조회 위주이므로 readOnly 추천
    public void collectRealtimeSessions() {
        List<DbConfig> targets = dbConfigRepository.findAllByUseYn("Y");

        // 가상 스레드 풀 생성
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            targets.forEach(target -> executor.submit(() -> {
                try {
                    // 상세 정보(Total, Active, Lock)를 포함한 DTO 수집
                    SessionStatusDto status = fetchDetailedStatus(target);
                    statusCache.put(target.getId(), status);
                } catch (Exception e) {
                    // 접속 실패 시 전용 에러 DTO 생성 (0, 0, 0 처리)
                    statusCache.put(target.getId(), SessionStatusDto.ofFail(target.getId()));
                }
            }));
        } // try-with-resources에 의해 모든 가상 스레드 종료 대기 후 close됨
    }

    private SessionStatusDto fetchDetailedStatus(DbConfig config) throws Exception {
        String url = String.format("jdbc:oracle:thin:@%s:%d:%s", config.getHost(), config.getPort(), config.getSid());

        // 1. 드라이버 레벨의 타임아웃 설정 (3,000개 수집 시 필수)
        DriverManager.setLoginTimeout(3);

        // 2. 통합 쿼리 (Total, Active, Lock)
        String sql = """
            SELECT 
                COUNT(*) as total_cnt,
                COUNT(DECODE(STATUS, 'ACTIVE', 1)) as active_cnt,
                COUNT(DECODE(BLOCKING_SESSION, NULL, NULL, 1)) as lock_cnt
            FROM V$SESSION 
            WHERE TYPE != 'BACKGROUND'
        """;

        try (Connection conn = DriverManager.getConnection(url, config.getUsername(), config.getPassword());
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return SessionStatusDto.ofSuccess(
                        config.getId(),
                        rs.getInt("total_cnt"),
                        rs.getInt("active_cnt"),
                        rs.getInt("lock_cnt")
                );
            }
        }
        throw new Exception("데이터 조회 실패");
    }

    public List<SessionStatusDto> getCurrentStatuses() {
        return new ArrayList<>(statusCache.values());
    }
}
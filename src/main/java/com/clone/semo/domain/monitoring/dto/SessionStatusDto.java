package com.clone.semo.domain.monitoring.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record SessionStatusDto(
        Long dbId,
        int totalSessions,    // 추가
        int activeSessions,   // 추가
        int lockCount,        // 추가
        boolean isAlive,
        String lastUpdatedAt
) {
    // 팩토리 메서드도 변경된 생성자에 맞춰 수정해야 합니다.
    public static SessionStatusDto ofSuccess(Long dbId, int total, int active, int lock) {
        return new SessionStatusDto(
                dbId,
                total,
                active,
                lock,
                true,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        );
    }

    public static SessionStatusDto ofFail(Long dbId) {
        return new SessionStatusDto(dbId, 0, 0, 0, false, "N/A");
    }
}
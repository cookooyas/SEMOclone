package com.clone.semo.domain.monitoring.dto;

import java.time.LocalDateTime;

public record MonitoringLogDto(
        int totalSessions,
        int activeSessions,
        int lockCount,
        LocalDateTime recordedAt
) {
    public static MonitoringLogDto from(com.clone.semo.domain.monitoring.entity.MonitoringLog log) {
        return new MonitoringLogDto(
                log.getTotalSessions(),
                log.getActiveSessions(),
                log.getLockCount(),
                log.getRecordedAt()
        );
    }
}

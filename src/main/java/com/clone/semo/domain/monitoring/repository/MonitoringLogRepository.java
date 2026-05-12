package com.clone.semo.domain.monitoring.repository;

import com.clone.semo.domain.monitoring.entity.MonitoringLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MonitoringLogRepository extends JpaRepository<MonitoringLog, Long> {
    List<MonitoringLog> findByDbConfigIdAndRecordedAtAfterOrderByRecordedAtAsc(Long dbConfigId, LocalDateTime time);
}

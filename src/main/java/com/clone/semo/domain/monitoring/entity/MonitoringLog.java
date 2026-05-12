package com.clone.semo.domain.monitoring.entity;

import com.clone.semo.domain.targetdb.entity.DbConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "monitoring_logs", indexes = @Index(name = "idx_recorded_at", columnList = "recordedAt"))
public class MonitoringLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "db_config_id")
    private DbConfig dbConfig;

    private int totalSessions;
    private int activeSessions;
    private int lockCount;
    private LocalDateTime recordedAt;

    public MonitoringLog(DbConfig dbConfig, int total, int active, int lock) {
        this.dbConfig = dbConfig;
        this.totalSessions = total;
        this.activeSessions = active;
        this.lockCount = lock;
        this.recordedAt = LocalDateTime.now();
    }
}

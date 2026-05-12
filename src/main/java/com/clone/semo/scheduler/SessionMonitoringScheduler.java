package com.clone.semo.scheduler;

import com.clone.semo.domain.monitoring.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class SessionMonitoringScheduler {
    private final MonitoringService monitoringService;

    @Scheduled(fixedDelay = 5000)
    public void run() {
        monitoringService.collectRealtimeSessions();
    }
}

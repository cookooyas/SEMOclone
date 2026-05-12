package com.clone.semo.domain.monitoring.controller.api;

import com.clone.semo.domain.monitoring.dto.MonitoringLogDto;
import com.clone.semo.domain.monitoring.dto.SessionStatusDto;
import com.clone.semo.domain.monitoring.repository.MonitoringLogRepository;
import com.clone.semo.domain.monitoring.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/monitoring")
@RequiredArgsConstructor
public class MonitoringRestController {
    private final MonitoringService monitoringService;
    private final MonitoringLogRepository logRepository;

    @GetMapping("/status")
    public List<SessionStatusDto> getStatus() {
        return monitoringService.getCurrentStatuses();
    }

    @GetMapping("/history/{dbId}")
    public List<MonitoringLogDto> getHistory(@PathVariable Long dbId) {
        return monitoringService.getHistoricalData(dbId);
    }
}
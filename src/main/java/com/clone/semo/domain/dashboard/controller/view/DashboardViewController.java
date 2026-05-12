package com.clone.semo.domain.dashboard.controller.view;

import com.clone.semo.domain.dashboard.service.DashboardService;
import com.clone.semo.domain.monitoring.service.MonitoringService;
import com.clone.semo.domain.targetdb.entity.DbConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardViewController {private final DashboardService dashboardService;

    private final MonitoringService monitoringService;

    @GetMapping()
    public String index(Model model) {
        List<DbConfig> dbList = dashboardService.getAllTargetDbs();
        model.addAttribute("dbList", dbList);
        model.addAttribute("message", "오라클 DB 모니터링 서비스에 오신 것을 환영합니다!");
        model.addAttribute("status", "서버 가동 중");
        return "dashboard/index";
    }

    @GetMapping("/chart/{id}")
    public String detailChart(@PathVariable Long id, Model model) {
        var dbConfig = monitoringService.findDbConfig(id);
        model.addAttribute("db", dbConfig);
        return "dashboard/chart";
    }
}

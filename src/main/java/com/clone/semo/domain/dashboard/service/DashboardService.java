package com.clone.semo.domain.dashboard.service;

import com.clone.semo.domain.targetdb.entity.DbConfig;
import com.clone.semo.domain.targetdb.repository.DbConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final DbConfigRepository dbConfigRepository;

    @Transactional(readOnly = true)
    public List<DbConfig> getAllTargetDbs() {
        return dbConfigRepository.findAllByUseYn("Y");
    }
}
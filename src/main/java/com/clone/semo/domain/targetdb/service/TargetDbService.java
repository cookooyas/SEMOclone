package com.clone.semo.domain.targetdb.service;

import com.clone.semo.domain.targetdb.dto.TargetDbRegisterForm;
import com.clone.semo.domain.targetdb.entity.DbConfig;
import com.clone.semo.domain.targetdb.repository.DbConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TargetDbService {

    private final DbConfigRepository dbConfigRepository;

    public void register(TargetDbRegisterForm form) {
        DbConfig dbConfig = DbConfig.builder()
                .alias(form.alias())
                .host(form.host())
                .port(form.port())
                .username(form.username())
                .password(form.password()) // 실제 서비스라면 암호화 필요
                .sid(form.sid())
                .useYn("Y")
                .build();

        dbConfigRepository.save(dbConfig);
    }

    @Transactional
    public void deleteTargetDb(Long id) {
        DbConfig dbConfig = dbConfigRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 DB 설정이 없습니다. id=" + id));

        dbConfig.markAsDeleted();
    }
}
package com.clone.semo.domain.targetdb.dto;

public record TargetDbRegisterForm(
        String alias,    // DB 별칭 (예: 운영서버-1)
        String host,     // IP 또는 도메인
        Integer port,    // 포트 (기본 1521)
        String username,
        String password,
        String sid       // Oracle SID 또는 Service Name
) {}
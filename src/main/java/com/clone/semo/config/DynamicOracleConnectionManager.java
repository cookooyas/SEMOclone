package com.clone.semo.config;

import com.clone.semo.domain.targetdb.entity.DbConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DynamicOracleConnectionManager {
    private final Map<Long, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    public JdbcTemplate getJdbcTemplate(DbConfig config) {
        DataSource dataSource = dataSourceMap.computeIfAbsent(config.getId(), id -> createDataSource(config));
        return new JdbcTemplate(dataSource);
    }

    private DataSource createDataSource(DbConfig config) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("oracle.jdbc.OracleDriver");

        String url = String.format("jdbc:oracle:thin:@%s:%d:%s",
                config.getHost(), config.getPort(), config.getSid());

        ds.setUrl(url);
        ds.setUsername(config.getUsername());
        ds.setPassword(config.getPassword());

        log.info(">>>> [{}] 새로운 오라클 커넥션 생성됨: {}", config.getAlias(), url);
        return ds;
    }
}

CREATE DATABASE semo_monitor DEFAULT CHARACTER SET utf8mb4;

USE semo_monitor;

CREATE TABLE db_config (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           alias VARCHAR(50) NOT NULL,      -- 구분용 이름 (예: 운영_ERP)
                           host VARCHAR(100) NOT NULL,     -- IP 주소
                           port INT DEFAULT 1521,          -- 포트
                           sid VARCHAR(50),                -- SID
                           service_name VARCHAR(50),       -- 또는 Service Name
                           username VARCHAR(50) NOT NULL,
                           password VARCHAR(100) NOT NULL,
                           use_yn CHAR(1) DEFAULT 'Y',     -- 모니터링 활성화 여부
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO db_config (alias, host, port, sid, username, password)
VALUES ('Local_Oracle', 'localhost', 1521, 'xe', 'system', 'oracle');

CREATE TABLE collected_log (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               db_id BIGINT NOT NULL,           -- db_config 테이블의 id
                               active_sessions INT,             -- 수집된 세션 수
                               collect_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (db_id) REFERENCES db_config(id)
);
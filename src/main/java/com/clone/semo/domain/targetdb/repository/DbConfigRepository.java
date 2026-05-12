package com.clone.semo.domain.targetdb.repository;

import com.clone.semo.domain.targetdb.entity.DbConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbConfigRepository extends JpaRepository<DbConfig, Long> {
    List<DbConfig> findAllByUseYn(String useYn);
}

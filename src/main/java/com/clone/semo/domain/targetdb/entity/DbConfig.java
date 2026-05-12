package com.clone.semo.domain.targetdb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="db_config")
public class DbConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alias;
    private String host;
    private int port;
    private String sid;
    private String serviceName;
    private String username;
    private String password;
    @Column(name = "use_yn")
    private String useYn;

    public void markAsDeleted() {
        this.useYn = "N";
    }
}

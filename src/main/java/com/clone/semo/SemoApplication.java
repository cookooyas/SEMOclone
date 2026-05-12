package com.clone.semo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
@EnableEncryptableProperties
public class SemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemoApplication.class, args);
    }

}

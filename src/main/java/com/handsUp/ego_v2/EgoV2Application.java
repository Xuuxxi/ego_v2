package com.handsUp.ego_v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableWebSecurity
@EnableAsync
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ServletComponentScan
@EnableTransactionManagement
public class EgoV2Application {

    public static void main(String[] args) {
        SpringApplication.run(EgoV2Application.class, args);
    }

}

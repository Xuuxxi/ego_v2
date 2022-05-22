package com.handsUp.ego_v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class EgoV2Application {

    public static void main(String[] args) {
        SpringApplication.run(EgoV2Application.class, args);
    }

}

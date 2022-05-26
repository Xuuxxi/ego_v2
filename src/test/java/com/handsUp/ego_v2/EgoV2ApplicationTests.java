package com.handsUp.ego_v2;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.handsUp.ego_v2.entity.SocketData;
import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.mapper.SocketMapper;
import com.handsUp.ego_v2.service.SocketService;
import com.handsUp.ego_v2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@EnableAsync
@ServletComponentScan
@EnableTransactionManagement
class EgoV2ApplicationTests {

    @Autowired
    SocketMapper socketMapper;

    @Autowired
    SocketService socketService;


    @Test
    void test(){

        SocketData socketData = new SocketData();
        socketData.setFrom(123L);
        socketData.setTo(456L);
        socketData.setSendTime(LocalDateTime.now());
        socketData.setMsg("hello");
        socketData.setIsRead(1);

        socketMapper.insert(socketData);

    }
}

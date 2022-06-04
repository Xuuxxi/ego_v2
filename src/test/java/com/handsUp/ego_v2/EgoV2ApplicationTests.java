package com.handsUp.ego_v2;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.SocketData;
import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.mapper.SocketMapper;
import com.handsUp.ego_v2.service.GoodService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@EnableAsync
@ServletComponentScan
@EnableTransactionManagement
class EgoV2ApplicationTests {



    @Autowired
    UserService userService;


    @Test
    void test01(){
        User user = new User();
        user.setId(1531215863825620994L);
        user.setUserNickName("test1");
        user.setUserName("2022");
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        user.setIsBanned(0);


        userService.updateById(user);

    }

    @Autowired
    GoodService goodService;

    @Test
    void test2(){
        Good good = new Good();
        good.setGoodName("apple");
        good.setGoodPrice(new BigDecimal(1.5));

        goodService.save(good);

    }

    @Test
    void test3(){
        Good good = new Good();
        good.setGoodName("apple");
        good.setGoodPrice(new BigDecimal(2.7));
        good.setId(1531227076227760129L);

        goodService.updateById(good);
    }
}

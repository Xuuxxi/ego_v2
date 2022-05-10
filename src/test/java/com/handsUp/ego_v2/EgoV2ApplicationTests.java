package com.handsUp.ego_v2;

import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class EgoV2ApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {

        User user = new User();
        user.setUserNickName("xiaoMing");
        user.setUserName("test1");
        user.setPassword("123456");
        user.setIsBanned(0);

        userService.save(user);

        //service可用
    }

    @Test
    void logTest(){
        log.info(null);
    }

}

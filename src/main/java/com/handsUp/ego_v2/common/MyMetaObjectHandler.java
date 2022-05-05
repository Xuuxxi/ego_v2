package com.handsUp.ego_v2.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/5
 */


/**
 * 都不用写啦！
 * 配置自动填充更新时间
 *
 *         employee.setCreateTime(LocalDateTime.now());
 *         employee.setUpdateTime(LocalDateTime.now());
 *
 *         Long empId = (Long) request.getSession().getAttribute("employee");
 *
 *         employee.setCreateUser(empId);
 *         employee.setUpdateUser(empId);
 *
 */

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert 自动填充...");

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update 自动填充...");

        metaObject.setValue("updateTime", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}

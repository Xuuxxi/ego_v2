package com.handsUp.ego_v2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.dto.UserDto;
import com.handsUp.ego_v2.entity.User;

// for dbs final test
public interface UserService extends IService<User> {
    public UserDto getInfo(Long id);
}

package com.handsUp.ego_v2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.mapper.UserMapper;
import com.handsUp.ego_v2.service.GetUserService;
import org.springframework.stereotype.Service;

// for dbs final test
@Service
public class GetUserServiceImpl extends ServiceImpl<UserMapper, User> implements GetUserService {
}

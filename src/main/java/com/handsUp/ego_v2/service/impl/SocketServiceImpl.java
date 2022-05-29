package com.handsUp.ego_v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.SocketData;
import com.handsUp.ego_v2.mapper.SocketMapper;
import com.handsUp.ego_v2.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: superdog
 * @Date: 2022/5/22
 */

@Service
public class SocketServiceImpl extends ServiceImpl<SocketMapper, SocketData> implements SocketService {

    @Autowired
    SocketMapper socketMapper;

}

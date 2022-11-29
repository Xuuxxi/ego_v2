package com.handsUp.ego_v2.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.SocketData;
import com.handsUp.ego_v2.mapper.SocketMapper;
import com.handsUp.ego_v2.service.SocketService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// for dbs final test

@Service
public class SocketServiceImpl extends ServiceImpl<SocketMapper, SocketData> implements SocketService {

    @Resource
    SocketMapper socketMapper;


    @Transactional
    @Override
    public void setRead(Long self, Long opposite) {
        LambdaUpdateWrapper<SocketData> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SocketData::getOrigin,opposite).eq(SocketData::getTarget,self)
                .set(SocketData::getIsRead,1);
        socketMapper.update(null,updateWrapper);
    }

    @Transactional
    @Override
    public List<SocketData> getDataList(Long from, Long to) {
        LambdaQueryWrapper<SocketData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.nested(i->i.eq(SocketData::getOrigin,from).eq(SocketData::getTarget,to))
                .or(i->i.eq(SocketData::getTarget,from).eq(SocketData::getOrigin,to))
                .orderByAsc(SocketData::getSendTime);
        

        return socketMapper.selectList(queryWrapper);
    }

    @Transactional
    @Override
    public List<Long> check(Long self) {
        LambdaQueryWrapper<SocketData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocketData::getTarget,self).eq(SocketData::getIsRead,0);

        HashSet<Long> userSet = new HashSet<>();
        socketMapper.selectList(queryWrapper).forEach(i->userSet.add(i.getOrigin()));

        return new ArrayList<Long>(userSet);
    }

    @Transactional
    @Override
    public List<Long> target(Long self) {
        LambdaQueryWrapper<SocketData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocketData::getOrigin,self);

        HashSet<Long> userSet = new HashSet<>();
        socketMapper.selectList(queryWrapper).forEach(i->userSet.add(i.getTarget()));
        queryWrapper.clear();
        queryWrapper.eq(SocketData::getTarget,self);
        socketMapper.selectList(queryWrapper).forEach(i->userSet.add(i.getOrigin()));

        return new ArrayList<Long>(userSet);
    }
}

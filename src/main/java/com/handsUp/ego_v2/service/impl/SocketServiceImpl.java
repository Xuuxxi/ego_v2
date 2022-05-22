package com.handsUp.ego_v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.SocketData;
import com.handsUp.ego_v2.mapper.SocketMapper;
import com.handsUp.ego_v2.service.SocketService;
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

    @Resource
    private SocketService socketService;

    @Transactional
    @Override
    public List<SocketData> getDataList(Long self,Long target) {
        LambdaQueryWrapper<SocketData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocketData::getFrom,self).eq(SocketData::getTo,target).and(
                i->i.eq(SocketData::getFrom,target).eq(SocketData::getTo,self)
        ).orderByAsc(SocketData::getSendTime);
        List<SocketData> dataList = socketService.list(queryWrapper);
        return dataList;
    }

    @Transactional
    @Override
    public List<Long> getUserList(Long userId) {
        LambdaQueryWrapper<SocketData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocketData::getTo,userId).eq(SocketData::getIsRead,0)
                .select(SocketData::getFrom).orderByDesc(SocketData::getSendTime);
        List<SocketData> socketDataList = socketService.list(queryWrapper);
        List<Long> userList = new ArrayList<>();
        socketDataList.forEach(o->userList.add(o.getFrom()));
        return userList;
    }
}

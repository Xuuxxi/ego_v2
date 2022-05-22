package com.handsUp.ego_v2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.entity.SocketData;

import java.util.List;

public interface SocketService extends IService<SocketData> {

    public List<SocketData> getDataList(Long self,Long target);

    public List<Long> getUserList(Long userId);
}

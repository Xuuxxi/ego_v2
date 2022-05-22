package com.handsUp.ego_v2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.entity.SocketData;

import java.util.List;

public interface SocketService extends IService<SocketData> {

    List<SocketData> getDataList(Long self,Long target);

    List<Long> getUserList(Long userId);

    void setRead(Long self,Long target);
}

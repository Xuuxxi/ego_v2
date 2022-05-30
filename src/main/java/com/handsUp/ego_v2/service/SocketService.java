package com.handsUp.ego_v2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.entity.SocketData;

import java.util.List;

public interface SocketService extends IService<SocketData> {

    //设置已读
    void setRead(Long self,Long opposite);

    //获得消息列表
    List<SocketData> getDataList(Long from,Long to);

    //检查未读列表
    List<Long> check(Long self);

    //获得用户所有已建立会话的用户列表
    List<Long> target(Long self);
}

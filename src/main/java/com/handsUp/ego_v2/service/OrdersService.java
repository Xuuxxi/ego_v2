package com.handsUp.ego_v2.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.dto.OrdersDto;
import com.handsUp.ego_v2.entity.Orders;

// for dbs final test
public interface OrdersService extends IService<Orders> {
    public void submit(Orders orders);

    public OrdersDto getDto(Long number);
}

package com.handsUp.ego_v2.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.dto.OrdersDto;
import com.handsUp.ego_v2.entity.Orders;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/14
 */
public interface OrdersService extends IService<Orders> {
    public void submit(Orders orders);

    public OrdersDto getDto(Long number);
}

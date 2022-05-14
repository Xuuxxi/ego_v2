package com.handsUp.ego_v2.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.OrderDetail;
import com.handsUp.ego_v2.mapper.OrderDetailMapper;
import com.handsUp.ego_v2.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/14
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}

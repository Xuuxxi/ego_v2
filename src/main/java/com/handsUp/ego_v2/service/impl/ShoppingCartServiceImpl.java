package com.handsUp.ego_v2.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.ShoppingCart;
import com.handsUp.ego_v2.mapper.ShoppingCartMapper;
import com.handsUp.ego_v2.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/14
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}

package com.handsUp.ego_v2.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.common.BaseContext;
import com.handsUp.ego_v2.common.CustomException;
import com.handsUp.ego_v2.dto.OrdersDto;
import com.handsUp.ego_v2.entity.*;
import com.handsUp.ego_v2.mapper.OrdersMapper;
import com.handsUp.ego_v2.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// for dbs final test
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Resource
    private AddressBookService addressBookService;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private UserService userService;
    @Resource
    private GoodService goodService;

    @Override
    @Transactional
    public void submit(Orders orders) {
        Long userId = BaseContext.getCurrentId();

        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,userId);

        List<ShoppingCart> carts = shoppingCartService.list(wrapper);
        if(carts == null || carts.size() == 0){
            throw new CustomException("无待结算商品");
        }

        User userInfo = userService.getById(userId);

        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if(addressBook == null) throw new CustomException("地址信息错误");

        long ordersId = IdWorker.getId();
        AtomicInteger amount = new AtomicInteger(0);

        List<OrderDetail> orderDetails = carts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(ordersId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setGoodId(item.getGoodId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        orders.setId(ordersId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));//总金额
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(ordersId));
        orders.setUserName(userInfo.getUserName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvince() == null ? "" : addressBook.getProvince())
                + (addressBook.getCity() == null ? "" : addressBook.getCity())
                + (addressBook.getDistrict() == null ? "" : addressBook.getDistrict())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));

        this.save(orders);

        orderDetailService.saveBatch(orderDetails);

        shoppingCartService.remove(wrapper);
    }

    @Override
    @Transactional
    public OrdersDto getDto(Long number) {
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getNumber,number);
        Orders order = this.getOne(wrapper);

        OrdersDto dto = new OrdersDto();
        BeanUtils.copyProperties(order,dto);

        Long buyerId = order.getUserId();
        dto.setBuyer(userService.getById(buyerId));

        LambdaQueryWrapper<OrderDetail> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(OrderDetail::getOrderId,order.getId());
        OrderDetail orderDetail = orderDetailService.getOne(wrapper1);

        Good good = goodService.getById(orderDetail.getGoodId());
        dto.setGood(good);

        Long sellerId = good.getSellerId();
        dto.setSeller(userService.getById(sellerId));
        return dto;
    }
}

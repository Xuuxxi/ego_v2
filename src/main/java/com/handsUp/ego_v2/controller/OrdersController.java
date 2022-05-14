package com.handsUp.ego_v2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.dto.OrdersDto;
import com.handsUp.ego_v2.entity.Orders;
import com.handsUp.ego_v2.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/14
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    /**
     * 查询所有订单信息/根据订单号查询
     * @param page
     * @param pageSize
     * @param number
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,Long number){
        log.info("order page...");
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(number != null, Orders::getNumber,number);

        ordersService.page(pageInfo,wrapper);
        return R.success(pageInfo);
    }

    /**
     * 订单提交
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> order(@RequestBody Orders orders){
        log.info("orders info = {}",orders);
        ordersService.submit(orders);
        return R.success("订单提交成功");
    }

    /**
     * 查看用户历史订单
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> userPage(int page,int pageSize){
        log.info("user page info ...");
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Orders::getOrderTime);

        ordersService.page(pageInfo,wrapper);
        return R.success(pageInfo);
    }

    /**
     * 改变配送状态
     * @param orders
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Orders orders){
        log.info("update orders...");
        ordersService.updateById(orders);
        return R.success("订单信息更新成功！");
    }

    /**
     * 根据订单号获取买卖家、商品信息、订单信息
     * @param number
     * @return
     */
    @GetMapping("/{number}")
    public R<OrdersDto> getDto(@PathVariable Long number){
        log.info("get ordersDto....");
        return R.success(ordersService.getDto(number));
    }
}

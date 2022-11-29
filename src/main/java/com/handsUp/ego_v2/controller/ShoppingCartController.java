package com.handsUp.ego_v2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.handsUp.ego_v2.common.BaseContext;
import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.entity.ShoppingCart;
import com.handsUp.ego_v2.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

// for dbs final test
@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    // for dbs final test
    @GetMapping("/list/{userId}")
    public R<List<ShoppingCart>> list(@PathVariable Long userId){
        log.info("shopping cart loading...");

        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, userId);
        wrapper.orderByDesc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(wrapper);
        return R.success(list);
    }

    // for dbs final test
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info("add ing...");

        Long userId = shoppingCart.getUserId();

        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,userId);

        Long goodId = shoppingCart.getGoodId();
        wrapper.eq(goodId != null,ShoppingCart::getGoodId,goodId);

        ShoppingCart one = shoppingCartService.getOne(wrapper);

        if(one != null){
            Integer number = one.getNumber();
            one.setNumber(number + 1);
            shoppingCartService.updateById(one);
        }else{
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            one = shoppingCart;
        }

        return R.success(one);
    }

    // for dbs final test
    @DeleteMapping("/clean/{userId}")
    public R<String> delete(@PathVariable Long userId){
        log.info("delete shoppingCart...");

        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, userId);

        shoppingCartService.remove(wrapper);
        return R.success("delete success!");
    }
}

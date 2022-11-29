package com.handsUp.ego_v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.dto.GoodDto;
import com.handsUp.ego_v2.dto.UserDto;
import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.GoodFlavor;
import com.handsUp.ego_v2.entity.Trade;
import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.mapper.UserMapper;
import com.handsUp.ego_v2.service.GoodFlavorService;
import com.handsUp.ego_v2.service.GoodService;
import com.handsUp.ego_v2.service.TradeService;
import com.handsUp.ego_v2.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// for dbs final test
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private GoodService goodService;
    @Resource
    private TradeService tradeService;
    @Resource
    private GoodFlavorService goodFlavorService;


    @Override
    @Transactional
    public UserDto getInfo(Long id) {
        User user = this.getById(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);

        LambdaQueryWrapper<GoodFlavor> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(GoodFlavor::getUserId,user.getId());
        wrapper1.orderByDesc(GoodFlavor::getUpdateTime);
        List<GoodFlavor> goodFlavors = goodFlavorService.list(wrapper1);

        List<Good> goods;
        goods = goodFlavors.stream().map((item) -> goodService.getById(item.getGoodId())).collect(Collectors.toList());

        userDto.setGoods(goods);

        LambdaQueryWrapper<Trade> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(Trade::getSellerId,user.getId());
        wrapper2.orderByDesc(Trade::getUpdateTime);
        userDto.setSellers(tradeService.list(wrapper2));

        LambdaQueryWrapper<Trade> wrapper3 = new LambdaQueryWrapper<>();
        wrapper3.eq(Trade::getBuyerId,user.getId());
        wrapper3.orderByDesc(Trade::getUpdateTime);
        userDto.setBuyers(tradeService.list(wrapper3));

        return userDto;
    }
}

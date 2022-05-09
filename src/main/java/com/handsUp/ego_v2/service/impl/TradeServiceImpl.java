package com.handsUp.ego_v2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.dto.TradeDto;
import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.Trade;
import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.mapper.TradeMapper;
import com.handsUp.ego_v2.service.GetUserService;
import com.handsUp.ego_v2.service.GoodService;
import com.handsUp.ego_v2.service.TradeService;
import com.handsUp.ego_v2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements TradeService {
    @Resource
    private GoodService goodService;
    @Resource
    private GetUserService getUserService;

    @Override
    @Transactional
    public TradeDto getByIdWithInfo(Long id) {
        Trade trade = this.getById(id);

        Good good = goodService.getById(trade.getGoodId());

        User seller = getUserService.getById(trade.getSellerId());

        User buyer = getUserService.getById(trade.getBuyerId());

        TradeDto tradeDto = new TradeDto();
        BeanUtils.copyProperties(trade,tradeDto);

        tradeDto.setBuyer(buyer);
        tradeDto.setGood(good);
        tradeDto.setSeller(seller);

        return tradeDto;
    }
}

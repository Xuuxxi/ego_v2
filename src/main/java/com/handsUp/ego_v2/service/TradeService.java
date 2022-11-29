package com.handsUp.ego_v2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.dto.TradeDto;
import com.handsUp.ego_v2.entity.Trade;

// for dbs final test
public interface TradeService extends IService<Trade> {
    public TradeDto getByIdWithInfo(Long id);
}

package com.handsUp.ego_v2.controller;

import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.dto.TradeDto;
import com.handsUp.ego_v2.entity.Trade;
import com.handsUp.ego_v2.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// for dbs final test
@RestController
@Slf4j
@RequestMapping("/trade")
public class TradeController {
    @Resource
    private TradeService tradeService;

    @GetMapping("/{id}")
    public R<TradeDto> get(@PathVariable Long id){
        log.info("getting tradeInfo ...");
        TradeDto info = tradeService.getByIdWithInfo(id);

        return R.success(info);
    }

    @PostMapping
    public R<String> save(@RequestBody Trade trade){
        log.info("Trade save...");
        trade.setTradeState(1);
        tradeService.save(trade);
        return R.success("新增交易记录成功!");
    }
}

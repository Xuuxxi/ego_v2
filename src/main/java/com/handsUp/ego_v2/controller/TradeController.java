package com.handsUp.ego_v2.controller;

import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.dto.TradeDto;
import com.handsUp.ego_v2.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
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
}

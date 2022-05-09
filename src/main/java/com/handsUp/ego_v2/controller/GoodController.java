package com.handsUp.ego_v2.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.dto.GoodDto;
import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.service.GoodService;
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
@RequestMapping("/good")
public class GoodController {
    @Resource
    private GoodService goodService;

    /**
     * 根据商品名字分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}/{name}")
    public R<Page> page(@PathVariable int page,@PathVariable int pageSize,@PathVariable String name){
        log.info("根据商品名字分页查询, page = {}, pageSize = {}",page,pageSize);

        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Good> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Good::getGoodName,name);
        //按更新时间降序排列
        wrapper.orderByDesc(Good::getUpdateTime);

        goodService.page(pageInfo,wrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据商品id查询商品 + 卖家
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<GoodDto> getGood(@PathVariable Long id){
        log.info("get goodInfo ...");
        GoodDto goodDto = goodService.getByIdWithUser(id);

        return R.success(goodDto);
    }
}
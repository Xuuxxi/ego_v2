package com.handsUp.ego_v2.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.dto.GoodDto;
import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.GoodFlavor;
import com.handsUp.ego_v2.service.GoodFlavorService;
import com.handsUp.ego_v2.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @Resource
    private GoodFlavorService goodFlavorService;

    /**
     * 根据商品名字分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}")
    public R<Page> page(@PathVariable int page,@PathVariable int pageSize,String name,String categoryName){
        log.info("根据商品名字分页查询, page = {}, pageSize = {}",page,pageSize);

        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Good> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null,Good::getGoodName,name);
        wrapper.eq(categoryName != null,Good::getGoodTypes,categoryName);
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

    /**
     * 添加商品
     * @param good
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Good good){
        log.info("save good ...");
        good.setIsPassed(0);
        goodService.save(good);
        return R.success("商品添加成功!");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("delete good...");
        goodService.removeWithFlavor(ids);
        return R.success("商品删除成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Good good){
        log.info("good update...");
        goodService.updateById(good);
        return R.success("商品信息更新成功！");
    }


    @PostMapping("/{goodId}")
    public R<String> star(HttpServletRequest request,@PathVariable Long goodId){
        Long userId = (Long) request.getSession().getAttribute("user");
        GoodFlavor goodFlavor = new GoodFlavor();
        goodFlavor.setGoodId(goodId);
        goodFlavor.setUserId(userId);

        goodFlavorService.save(goodFlavor);
        return R.success("关注成功!");
    }
}

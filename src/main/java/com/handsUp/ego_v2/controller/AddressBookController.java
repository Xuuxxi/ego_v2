package com.handsUp.ego_v2.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.handsUp.ego_v2.common.BaseContext;
import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.entity.AddressBook;
import com.handsUp.ego_v2.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/14
 */
@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {
    @Resource
    private AddressBookService addressBookService;

    /**
     * 获取当前用户所有地址
     * @return
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(){
        log.info("list addressBook...");

        Long userId = BaseContext.getCurrentId();

        LambdaQueryWrapper<AddressBook> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(AddressBook::getUserId,userId);
        wrapper.orderByDesc(AddressBook::getUpdateTime);
        List<AddressBook> list = addressBookService.list(wrapper);

        return R.success(list);
    }

    /**
     * 保存新地址
     * @param addressBook
     * @return
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        log.info("saving addressBook ...");
        addressBook.setUserId(BaseContext.getCurrentId());

        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 设置默认地址
     * @return
     */
    @GetMapping("/default")
    public R<AddressBook> getDefault(){
        log.info("getting default...");
        Long userId = BaseContext.getCurrentId();

        LambdaQueryWrapper<AddressBook> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AddressBook::getUserId,userId);
        wrapper.eq(AddressBook::getIsDefault,1);

        AddressBook addressBook = addressBookService.getOne(wrapper);

        if(addressBook != null) return R.success(addressBook);
        else  return R.error("没有默认地址");
    }

    /**
     * 更改默认地址
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        log.info("set default...");

        Long userId = BaseContext.getCurrentId();

        //可以用作更新的wrapper，之前都是使用的查询wrapper
        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId,userId);
        wrapper.set(AddressBook::getIsDefault,0);

        addressBookService.update(wrapper);

        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);

        return R.success(addressBook);
    }

    /**
     * 根据id查找地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<AddressBook> getAdBook(@PathVariable Long id){
        log.info("getting adBook...");
        AddressBook addressBook = addressBookService.getById(id);

        if(addressBook == null) return R.error("未查找到该对象");

        return R.success(addressBook);
    }

    /**
     * 更新地址
     * @param addressBook
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody AddressBook addressBook){
        log.info("update address info...");
        addressBookService.updateById(addressBook);
        return R.success("更新成功！");
    }

    /**
     * 删除地址
     * 可批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("delete address info...");

        LambdaQueryWrapper<AddressBook> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(AddressBook::getId,ids);

        addressBookService.remove(wrapper);

        return R.success("删除成功！");
    }
}

package com.handsUp.ego_v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.dto.GoodDto;
import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.GoodFlavor;
import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.mapper.GoodMapper;
import com.handsUp.ego_v2.service.GetUserService;
import com.handsUp.ego_v2.service.GoodFlavorService;
import com.handsUp.ego_v2.service.GoodService;
import com.handsUp.ego_v2.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {
    @Resource
    private GetUserService getUserService;
    @Resource
    private GoodFlavorService goodFlavorService;

    @Override
    @Transactional
    public GoodDto getByIdWithUser(Long id) {
        Good good = this.getById(id);

        User user = getUserService.getById(good.getSellerId());

        GoodDto goodDto = new GoodDto();
        BeanUtils.copyProperties(good,goodDto);
        goodDto.setUser(user);

        LambdaQueryWrapper<GoodFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodFlavor::getGoodId,id);

        int flag = 0;
        List<GoodFlavor> list = goodFlavorService.list(wrapper);
        for(GoodFlavor i : list){
            if(i.getUserId().equals(user.getId())){
                flag = 1;
                break;
            }
        }
        goodDto.setIsStared(flag);

        return goodDto;
    }
}

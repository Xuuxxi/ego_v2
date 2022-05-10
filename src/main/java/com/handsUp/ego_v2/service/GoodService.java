package com.handsUp.ego_v2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.handsUp.ego_v2.dto.GoodDto;
import com.handsUp.ego_v2.entity.Good;

import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
public interface GoodService extends IService<Good> {
    public GoodDto getByIdWithUser(Long id);

    public void removeWithFlavor(List<Long> ids);
}

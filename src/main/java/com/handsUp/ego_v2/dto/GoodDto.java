package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.GoodFlavor;
import com.handsUp.ego_v2.entity.User;
import lombok.Data;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
@Data
public class GoodDto extends Good {
    private User user;

    private Integer isStared;
}

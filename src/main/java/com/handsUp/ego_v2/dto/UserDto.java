package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.Trade;
import com.handsUp.ego_v2.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
@Data
public class UserDto extends User {
    private List<Good> goods;
    private List<Trade> buyers;
    private List<Trade> sellers;
}

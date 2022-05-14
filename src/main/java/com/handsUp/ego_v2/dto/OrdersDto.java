package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.Orders;
import com.handsUp.ego_v2.entity.User;
import lombok.Data;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/14
 */
@Data
public class OrdersDto extends Orders {
    private User buyer;
    private User seller;
    private Good good;
}

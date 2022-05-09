package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.Trade;
import com.handsUp.ego_v2.entity.User;
import lombok.Data;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
@Data
public class TradeDto extends Trade {
    private User seller;

    private User buyer;

    private Good good;
}

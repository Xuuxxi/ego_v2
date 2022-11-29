package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.Trade;
import com.handsUp.ego_v2.entity.User;
import lombok.Data;

// for dbs final test
@Data
public class TradeDto extends Trade {
    private User seller;

    private User buyer;

    private Good good;
}

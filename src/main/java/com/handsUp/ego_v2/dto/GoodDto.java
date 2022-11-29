package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.Good;
import com.handsUp.ego_v2.entity.GoodFlavor;
import com.handsUp.ego_v2.entity.User;
import lombok.Data;

// for dbs final test
@Data
public class GoodDto extends Good {
    private User user;

    private Integer isStared;
}

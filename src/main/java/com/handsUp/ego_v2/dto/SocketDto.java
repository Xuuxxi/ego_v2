package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.SocketData;
import lombok.Data;

import java.util.List;

// for dbs final test

@Data
public class SocketDto {
    private List<SocketData> dataList;
}

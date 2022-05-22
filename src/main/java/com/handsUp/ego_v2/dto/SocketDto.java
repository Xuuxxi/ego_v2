package com.handsUp.ego_v2.dto;

import com.handsUp.ego_v2.entity.SocketData;
import lombok.Data;

import java.util.List;

/**
 * @Author: superdog
 * @Date: 2022/5/22
 */

@Data
public class SocketDto {
    private List<SocketData> dataList;
}

package com.handsUp.ego_v2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: superdog
 * @Date: 2022/5/22
 */

@Data
@TableName("socket_data")
public class SocketData implements Serializable {

    private Long from;

    private Long to;

    private LocalDateTime sendTime;

    private Integer isRead;

    private String jsonMsg;

}

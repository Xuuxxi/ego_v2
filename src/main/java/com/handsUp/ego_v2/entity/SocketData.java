package com.handsUp.ego_v2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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

    private static final long serialVersionUID = 1L;

    @TableField("from")
    private Long from;

    @TableField("to")
    private Long to;

    @TableField("send_time")
    private LocalDateTime sendTime;

    @TableField("msg")
    private String msg;

    @TableField("is_read")
    private Integer isRead;



}

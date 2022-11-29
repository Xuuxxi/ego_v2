package com.handsUp.ego_v2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

// for dbs final test

@Data
@TableName("socket_data")
public class SocketData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("origin")
    private Long origin;

    @TableField("target")
    private Long target;

    @TableField("send_time")
    private LocalDateTime sendTime;

    @TableField("msg")
    private String msg;

    @TableField("is_read")
    private Integer isRead;



}

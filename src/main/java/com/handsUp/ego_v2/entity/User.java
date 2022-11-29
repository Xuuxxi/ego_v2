package com.handsUp.ego_v2.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

// for dbs final test

@Data
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userNickName;

    private String userName;

    @TableField(value = "user_pwd")
    private String password;

    private String userPtUrl;

    private String userInfo;

    private String userAd;

    private Integer isBanned;

    private Long banTime;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}

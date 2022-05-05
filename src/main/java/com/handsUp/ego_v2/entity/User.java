package com.handsUp.ego_v2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/5
 */

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userNickName;

    private String userName;

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

package com.handsUp.ego_v2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/5
 */

@Data
@TableName("good")
public class Good implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String goodName;

    private BigDecimal goodPrice;

    private String goodInfo;

    private String goodTypes;

    private String goodPtUrl;

    private Long sellerId;

    private String sellerAd;

    private Integer isPassed;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}

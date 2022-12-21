package com.cloud.jack.core;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.jack.core.annotation.FiledCache;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 基础核心-实体类
 *
 * @author ML
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreModel<T extends Model<?>> extends Model<T> {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @FiledCache(prefix = "SYS_USER")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @FiledCache(prefix = "SYS_USER")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @OrderBy
    private Date updateTime;

    private Boolean delFlag;



}

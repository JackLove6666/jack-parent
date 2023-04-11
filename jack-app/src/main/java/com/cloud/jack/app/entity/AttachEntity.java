package com.cloud.jack.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_attach_t")
public class AttachEntity  {

    @TableId(value="id",type = IdType.ASSIGN_ID)
    Long id;
    /**
     * 文件下载地址
     */
    private String fileId;

    /**
     * oss地址
     */
    private String filePath;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件大小
     */
    private  String fileSize;

    /**
     * 文件类型
     */
    private  String fileType;

    /**
     * 文件描述
     */
    private  String description;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT )
    String  createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT )
    Date createDate;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.DEFAULT )
    String  updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE )
    Date   updateDate;

    /**
     * 是否有效(0:有效，1：删除)
     */
    int   enable;
    /**
     * 租户编码
     */
    @TableField(fill = FieldFill.INSERT)
    String  tenant;
}

/*
 *    Copyright (c) 2018-2035, Apemans All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the trob4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: ApemansIT (itsupport@apemans.com)
 */
package com.cloud.jack.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 源数据报告上传管理
 *
 * @author trobs code generator
 * @date 2022-10-11 14:55:59
 */
@Data
@TableName("source_upload_report_manage")
@ApiModel(value = "源数据报告上传管理")
public class SourceUploadReportManage extends Model<SourceUploadReportManage> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", example = "")
    @TableId
    private Long sourceUploadReportManageId;
    /**
     * 报告类型
     */
    @ApiModelProperty(value = "报告类型", example = "")
    private String billType;
    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名", example = "")
    private String fileName;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径", example = "")
    private String filePath;
    /**
     * 文件md5值
     */
    @ApiModelProperty(value = "文件md5值", example = "")
    private String fileMd5;
    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号", example = "")
    private String batchNo;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", example = "")
    private String status;
    /**
     * 失败原因
     */
    @ApiModelProperty(value = "失败原因", example = "")
    private String failMsg;
    /**
     * 提示消息
     */
    @ApiModelProperty(value = "提示消息", example = "")
    private String tipMsg;
    /**
     * 上传人
     */
    @ApiModelProperty(value = "上传人", example = "")
    private Integer uploadBy;
    /**
     * 上传时间
     */
    @ApiModelProperty(value = "上传时间", example = "")
    private Date uploadTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", example = "")
    private Integer createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", example = "")
    private Date createTime;
    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "最后更新人", example = "")
    private Integer updateBy;
    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间", example = "")
    private Date updateTime;

    @TableField(exist = false)
    private Map<String, Object> sendMqMap = new HashMap<>();

    @TableField(exist = false)
    private MultipartFile file;

    @TableField(exist = false)
    private String billTypeName;

    @TableField(exist = false)
    private String uploadName;

}

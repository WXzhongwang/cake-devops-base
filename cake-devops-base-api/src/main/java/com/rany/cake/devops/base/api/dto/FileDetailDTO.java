package com.rany.cake.devops.base.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * sftp 文件详情响应
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 18:36
 */
@Data
public class FileDetailDTO {

    /**
     * 名称
     */
    private String name;

    /**
     * 绝对路径
     */
    private String path;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 文件大小(byte)
     */
    private Long sizeByte;

    /**
     * 属性
     */
    private String attr;

    /**
     * 10进制表现的8进制权限
     */
    private Integer permission;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 组id
     */
    private Integer gid;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 是否为目录
     */
    private Boolean isDir;

    /**
     * 是否安全
     */
    private Boolean isSafe;

}

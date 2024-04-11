package com.rany.cake.devops.base.util.sftp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * sftp 操作
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/26 12:08
 */
@Getter
@AllArgsConstructor
public enum SftpTransferType {

    /**
     * 10 上传
     */
    UPLOAD(10, "上传"),

    /**
     * 20 下载
     */
    DOWNLOAD(20, "下载"),

    /**
     * 30 传输
     */
    TRANSFER(30, "传输"),

    /**
     * 40 打包
     */
    PACKAGE(40, "打包"),

    ;

    private final Integer type;

    private final String label;

    public static SftpTransferType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (SftpTransferType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}

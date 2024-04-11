package com.rany.cake.devops.base.util.sftp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * sftp通知类型
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/27 0:57
 */
@Getter
@AllArgsConstructor
public enum SftpNotifyType {

    /**
     * 添加任务
     */
    ADD(10),

    /**
     * 进度以及速率
     */
    PROGRESS(20),

    /**
     * 修改状态
     */
    CHANGE_STATUS(30),

    ;

    private final Integer type;

    public static SftpNotifyType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (SftpNotifyType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}

package com.rany.cake.devops.base.service.exception;

import cn.hutool.core.util.StrUtil;
import com.rany.uic.common.exception.enums.ErrorCodeEnum;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/13 22:36
 * @email 18668485565163.com
 */
public enum DevOpsErrorMessage {

    OPS_SUPPORTED_ERROR("50001", "尚未支持的集群类型"),
    OPS_ENV_SUPPORTED_ERROR("50002", "尚未支持的环境标签"),
    OPS_CONNECTED_ERROR("50003", "连接失败"),

    ;

    private final String code;
    private final String message;

    public String getCode() {
        return StrUtil.join("-", new Object[]{ErrorCodeEnum.OUT.getCode(), this.code});
    }

    public String getMessage() {
        return this.message;
    }

    private DevOpsErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

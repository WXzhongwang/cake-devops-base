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
public enum K8sErrorMessage {

    K8S_SUPPORTED_ERROR("50001", "尚未支持的类型"),
    K8S_CLIENT_ERROR("50001", "K8S构建客户端异常");

    private final String code;
    private final String message;

    public String getCode() {
        return StrUtil.join("-", new Object[]{ErrorCodeEnum.OUT.getCode(), this.code});
    }

    public String getMessage() {
        return this.message;
    }

    private K8sErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

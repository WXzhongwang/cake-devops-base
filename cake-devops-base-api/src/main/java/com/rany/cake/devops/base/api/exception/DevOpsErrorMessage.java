package com.rany.cake.devops.base.api.exception;

import com.cake.framework.common.exception.ErrorCodeEnum;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/13 22:36
 * @email 18668485565163.com
 */
public enum DevOpsErrorMessage {

    APP_NOT_FOUND("50000", "应用未找到"),
    CLUSTER_NOT_FOUND("50000", "集群未找到"),
    ENV_DUPLICATED("50000", "环境冲突"),
    OPS_SUPPORTED_ERROR("50001", "尚未支持的集群类型"),
    OPS_ENV_SUPPORTED_ERROR("50002", "尚未支持的环境标签"),
    OPS_CONNECTED_ERROR("50003", "连接失败"),

    ;

    private final String code;
    @Getter
    private final String message;

    public String getCode() {
        return StringUtils.join("-", new Object[]{ErrorCodeEnum.BIZ.getCode(), this.code});
    }

    private DevOpsErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

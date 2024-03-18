package com.rany.cake.devops.base.api.exception;

import com.cake.framework.common.exception.ErrorCodeEnum;
import com.cake.framework.common.exception.ResponseCode;
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
public enum DevOpsErrorMessage implements ResponseCode {

    APP_NOT_FOUND("30000", "应用未找到"),
    CLUSTER_NOT_FOUND("31000", "集群未找到"),
    ENV_NOT_FOUND("32000", "环境未找到"),
    APPROVAL_NOT_FOUND("33000", "审批未找到"),
    APPROVAL_NOT_APPROVED("34000", "审批未通过"),
    RELEASE_NOT_FOUND("35000", "发布未找到"),
    ENV_DUPLICATED("36000", "环境冲突"),
    OPS_SUPPORTED_ERROR("37000", "尚未支持的集群类型"),
    OPS_ENV_SUPPORTED_ERROR("38000", "尚未支持的环境标签"),
    OPS_CONNECTED_ERROR("39000", "连接失败"),
    PROXY_NOT_FOUND("39000", "代理未找到"),
    HOOK_NOT_FOUND("40000", "webhook未找到"),
    MACHINE_ENV_NOT_FOUND("40000", "主机环境未找到"),
    SCRIPT_TEMPLATE_NOT_FOUND("41000", "模版未找到"),
    ALARM_GROUP_NOT_FOUND("42000", "告警组未找到"),
    AGENT_STATUS_RUNNING("46000", "Agent运行中"),


    TIMEOUT_EXCEPTION_MESSAGE("", "连接超时"),
    
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

    @Override
    public String getErrorCode() {
        return this.getCode();
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}

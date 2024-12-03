package com.rany.cake.devops.base.api.exception;

import com.cake.framework.common.exception.ErrorCodeEnum;
import com.cake.framework.common.exception.ResponseCode;
import lombok.Getter;

/**
 * DevOpsErrorMessage
 *
 * @author zhongshengwang
 * @description DevOpsErrorMessage
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
    AGENT_STATUS_RUNNING("46000", "Agent启动中"),
    AGENT_FILE_NON_EXIST("46001", "插件包不存在 请确认文件路径: {}"),
    SYSTEM_ENV_NOT_FOUND("47000", "系统环境未找到"),
    TIMEOUT_EXCEPTION_MESSAGE("48000", "连接超时"),
    HOST_NOT_FOUND("49000", "主机未找到"),
    MEMBER_DUPLICATED("51000", "项目成员已存在"),
    SFTP_TOKEN_EMPTY("52000", "token为空"),
    SFTP_TOKEN_EXPIRE("53000", "token过期"),
    USER_MENU_INFO_ERROR("54000", "获取用户信息失败"),
    DEP_NOT_FOUND("55000", "应用未找到"),
    ;

    private final String code;
    @Getter
    private final String message;

    private DevOpsErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return ErrorCodeEnum.BIZ.getCode() + "-" + code;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}

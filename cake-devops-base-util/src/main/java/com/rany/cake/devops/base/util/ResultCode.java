package com.rany.cake.devops.base.util;


import com.rany.cake.toolkit.lang.wrapper.CodeInfo;

/**
 * wrapper 返回 code
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/2 9:48
 */
public enum ResultCode implements CodeInfo {

    /**
     * 未认证
     */
    UNAUTHORIZED("700", MessageConst.UNAUTHORIZED),

    /**
     * 无权限
     */
    NO_PERMISSION("710", MessageConst.NO_PERMISSION),

    /**
     * 文件未找到
     */
    FILE_MISSING("720", MessageConst.FILE_MISSING),

    /**
     * IP封禁
     */
    IP_BAN("730", MessageConst.IP_BAN),

    /**
     * 用户禁用
     */
    USER_DISABLED("740", MessageConst.USER_DISABLED),

    /**
     * 非法访问
     */
    ILLEGAL_ACCESS("750", MessageConst.ILLEGAL_ACCESS),

    ;

    private final String code;

    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}

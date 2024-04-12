package com.rany.cake.devops.plugin.common.constant;


import com.rany.cake.toolkit.lang.wrapper.CodeInfo;

/**
 * 返回code
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/2 9:48
 */
public enum ResultCode implements CodeInfo {

    /**
     * 非法访问
     */
    INVALID_ACCESS_TOKEN(1010, MessageConst.INVALID_ACCESS_TOKEN),

    ;

    private final int code;

    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}

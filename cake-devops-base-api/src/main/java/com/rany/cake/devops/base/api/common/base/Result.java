package com.rany.cake.devops.base.api.common.base;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/15 22:22
 * @email 18668485565163.com
 */

import java.io.Serializable;

/**
 * 前端统一返回格式
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/11/15 22:21
 * @email 18668485565163.com
 */
public final class Result<T> implements Serializable {

    private static final long serialVersionUID = 1878782750820670456L;

    private String requestId = "";
    private boolean success;
    private String code;
    private String msg;
    private T data;

    public Result(boolean success) {
        this.success = success;
        this.code = "200";
        this.msg = "处理成功";
    }

    public Result(String code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> succeed() {
        return new Result<>(true, "200", "处理成功");
    }

    public static <T> Result<T> succeed(T data) {
        return new Result<>(true, "200", "处理成功", data);
    }

    public static <T> Result<T> succeed(String code, String msg, T data) {
        return new Result<>(true, code, msg, data);
    }

    public static <T> Result<T> fail(String code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> fail() {
        return new Result<>(false);
    }


    @Override
    public String toString() {
        return "Result{" +
                "requestId='" + requestId + '\'' +
                ", success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 其他辅助方法

    public Result<T> requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }
}

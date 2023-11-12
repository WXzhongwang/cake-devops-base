package com.rany.cake.devops.base.api.exception;

/**
 * DevOpsException
 *
 * @author zhongshengwang
 * @description DevOpsException
 * @date 2023/2/18 22:28
 * @email 18668485565163.com
 */
public class DevOpsException extends RuntimeException {

    public DevOpsException(DevOpsErrorMessage devOpsErrorMessage) {
        super(devOpsErrorMessage.getMessage());
    }
}

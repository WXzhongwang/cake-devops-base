package com.rany.cake.devops.base.service.exception;

import com.rany.uic.common.exception.BusinessException;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/18 22:28
 * @email 18668485565163.com
 */
public class DevOpsException extends BusinessException {

    public DevOpsException(DevOpsErrorMessage devOpsErrorMessage) {
        super(devOpsErrorMessage.getCode(), devOpsErrorMessage.getMessage());
    }
}

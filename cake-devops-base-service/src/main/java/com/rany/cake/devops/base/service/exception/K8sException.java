package com.rany.cake.devops.base.service.exception;

import com.rany.uic.common.exception.ManagerException;

/**
 * K8sException
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/13 22:33
 * @email 18668485565163.com
 */
public class K8sException extends ManagerException {
    public K8sException(K8sErrorMessage errorMessage) {
        super(errorMessage.getCode(), errorMessage.getMessage());
    }
}

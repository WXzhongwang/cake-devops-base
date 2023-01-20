package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务归属
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 20:14
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessOwnership extends BaseValueObject {

    /**
     * bu
     */
    private String businessUnit;

    /**
     * bu缩写
     */
    private String bu;

    /**
     * 部门
     */
    private String department;
}

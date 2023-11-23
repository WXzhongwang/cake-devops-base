package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 业务归属
 *
 * @author zhongshengwang
 * @description 业务归属
 * @date 2023/1/20 20:14
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BusinessOwnership extends BaseValueObject {

    /**
     * 部门缩写
     */
    private String departmentAbbreviation;

    /**
     * 部门
     */
    private String department;
}

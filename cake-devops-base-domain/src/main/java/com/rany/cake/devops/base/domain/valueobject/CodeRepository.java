package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/12 23:04
 * @email 18668485565163.com
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CodeRepository extends BaseValueObject {

    private String repo;

    private String defaultBranch;
}

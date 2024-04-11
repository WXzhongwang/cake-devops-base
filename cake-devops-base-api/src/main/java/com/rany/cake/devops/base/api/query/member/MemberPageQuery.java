package com.rany.cake.devops.base.api.query.member;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户可用人员查询
 *
 * @author zhongshengwang
 * @description 租户可用人员查询
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberPageQuery extends BasePageQuery {
    private String name;
}

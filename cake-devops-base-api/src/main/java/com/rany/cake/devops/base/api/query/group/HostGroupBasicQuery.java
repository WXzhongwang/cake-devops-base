package com.rany.cake.devops.base.api.query.group;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询主机组
 *
 * @author zhongshengwang
 * @description 查询主机组
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HostGroupBasicQuery extends BaseQuery {

    private String hostGroupId;
}

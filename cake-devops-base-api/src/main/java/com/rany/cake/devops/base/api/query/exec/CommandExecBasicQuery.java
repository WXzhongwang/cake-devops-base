package com.rany.cake.devops.base.api.query.exec;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/14 16:44
 * @slogon 找到银弹
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommandExecBasicQuery extends BaseQuery {
    private Long id;
}

package com.rany.cake.devops.base.api.query.exec;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/14 16:45
 * @slogon 找到银弹
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommandExecPageQuery extends BasePageQuery {
    private String hostId;
    private Integer execStatus;
    private Integer execType;
    private String accountId;
    private List<String> hostIds;
}

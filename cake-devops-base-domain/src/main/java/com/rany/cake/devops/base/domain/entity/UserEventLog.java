package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * UserEventLog
 *
 * @author zhongshengwang
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEventLog extends BaseEntity<Long> {
    protected Long id;
    private String userId;
    private String username;
    private Integer eventClassify;
    private Integer eventType;
    private String logInfo;
    private String paramsJson;

    private Integer execResult;
}
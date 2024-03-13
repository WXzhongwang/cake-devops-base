package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 站内信
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class WebSideMessage extends BaseEntity<Long> {

    private Byte messageClassify;
    private Integer messageType;
    private Byte readStatus;
    private Long toUserId;
    private String toUserName;
    private String relId;
    private String sendMessage;
}

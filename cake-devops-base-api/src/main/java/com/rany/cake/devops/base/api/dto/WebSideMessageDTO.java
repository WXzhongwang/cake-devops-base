package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class WebSideMessageDTO extends DTO {
    private Long id;
    private Byte messageClassify;
    private Integer messageType;
    private Byte readStatus;
    private Long toUserId;
    private String toUserName;
    private Long relId;
    private String sendMessage;
}

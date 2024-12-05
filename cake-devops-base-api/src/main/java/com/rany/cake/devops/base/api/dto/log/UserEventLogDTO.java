package com.rany.cake.devops.base.api.dto.log;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserEventLogDTO extends DTO {
    private String userId;
    private String username;
    private Integer eventClassify;
    private String eventClassifyName;
    private Integer eventType;
    private String eventTypeName;
    private String logInfo;
    private String paramsJson;

    private Integer execResult;

    protected Date gmtCreate;
    protected Date gmtModified;
    protected String creator;
    protected String modifier;
    protected String isDeleted;
}

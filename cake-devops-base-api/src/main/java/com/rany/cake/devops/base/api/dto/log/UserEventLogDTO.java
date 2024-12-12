package com.rany.cake.devops.base.api.dto.log;

import com.rany.cake.devops.base.api.common.base.DTO;
import com.rany.cake.devops.base.api.common.enums.EventClassify;
import com.rany.cake.devops.base.api.common.enums.EventType;
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

    public String getEventClassifyName() {
        if (eventClassify != null) {
            return EventClassify.of(eventClassify).getLabel();
        }
        return eventClassifyName;
    }

    public String getEventTypeName() {
        if (eventType != null) {
            return EventType.of(eventType).getLabel();
        }
        return eventTypeName;
    }

}

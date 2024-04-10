package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ScriptTemplateDTO extends DTO {

    private Long id;
    private String templateName;
    private String templateValue;
    private String description;
    private Date gmtCreate;
    private Date gmtModified;

}

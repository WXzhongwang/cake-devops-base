package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class HostGroupTreeDTO extends DTO {
    private String hostGroupId;
    private String name;
    private String parentId;
    private Integer sort;

    private List<HostGroupTreeDTO> children;
}

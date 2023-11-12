package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HostGroupDTO extends DTO {
    private Long id;
    private String name;
    private Long parentId;
    private Integer sort;

    //private List<HostDTO> hosts;
}

package com.rany.cake.devops.base.service.cloud.dto;

import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResourceCmd {
    private String namespace;
    private String deploymentName;
    private ResourceStrategy resourceStrategy;
}

package com.rany.cake.devops.base.service.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteIngressCmd {

    /**
     * ingressName
     */
    private String ingressName;
    /**
     * 命名空间
     */
    private String namespace;
}

package com.rany.cake.devops.base.service.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteServiceCmd {

    /**
     * 发布服务名
     */
    private String serviceName;
    /**
     * 命名空间
     */
    private String namespace;
}

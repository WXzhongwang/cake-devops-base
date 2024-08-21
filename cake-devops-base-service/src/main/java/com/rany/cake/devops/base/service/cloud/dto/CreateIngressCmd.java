package com.rany.cake.devops.base.service.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateIngressCmd {
    private String namespace;
    /**
     * 发布服务名
     */
    private String serviceName;
    /**
     * 服务端口
     */
    private Integer servicePort;

    /**
     * 域名
     */
    private List<String> domains;

    /**
     * ingress name
     */
    private String ingressName;
}

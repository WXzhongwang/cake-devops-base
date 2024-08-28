package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseValueObject;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class ServiceValueObject extends BaseValueObject {
    /**
     * 发布服务名
     */
    private String serviceName;
    /**
     * 服务端口
     */
    private Integer servicePort;
    /**
     * 容器端口
     */
    private Integer containerPort;

    /**
     * 服务协议
     * TCP （默认）
     * UDP
     */
    private String serviceProtocol;
    /**
     * 默认服务类型ClusterIP、NodePort、LoadBalancer（暂不支持）
     * ClusterIP，表示服务只能在集群内部访问
     */
    private String serviceType;
    /**
     * 具体而言，NodePort会在指定的端口范围（通常是30000到32767）内分配一个端口，
     * 并将这一端口映射到所选择的Pod上。这样，当流量发送到集群中任一节点的该端口时，Kubernetes会将流量转发到对应的Pod。
     */
    private Integer nodePort;

}

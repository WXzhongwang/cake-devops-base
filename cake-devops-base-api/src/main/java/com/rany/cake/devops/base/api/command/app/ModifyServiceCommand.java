package com.rany.cake.devops.base.api.command.app;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ModifyServiceCommand
 *
 * @author zhongshengwang
 * @description configMap
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyServiceCommand extends BaseCommand {
    private String envId;

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

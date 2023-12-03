package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 命名空间
 *
 * @author zhongshengwang
 * @description 集群
 * @date 2023/2/18 22:40
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class NamespaceDomainService {
    private final NameSpaceRepository nameSpaceRepository;


    /**
     * 创建命名空间
     *
     * @param namespace
     */
    public void save(Namespace namespace) {
        nameSpaceRepository.save(namespace);
    }

    public void update(Namespace namespace) {
        nameSpaceRepository.update(namespace);
    }

    public Namespace getCluster(NamespaceId namespaceId) {
        return nameSpaceRepository.find(namespaceId);
    }

    public List<Namespace> listNamespace(ClusterId clusterId) {
        return nameSpaceRepository.listNamespace(clusterId);
    }
}

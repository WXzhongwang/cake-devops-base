package com.rany.cake.devops.base.api.service;

import com.rany.cake.devops.base.api.command.namespace.CreateNamespaceCommand;
import com.rany.cake.devops.base.api.dto.NamespaceDTO;
import com.rany.cake.devops.base.api.query.namespace.NamespaceQuery;

import java.util.List;

/**
 * 命名空间服务
 *
 * @author zhongshengwang
 * @description 命名空间服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface NamespaceService {

    /**
     * 创建命名空间
     *
     * @param createAppCommand 创建命名空间
     * @return 命名空间ID
     */
    String createNamespace(CreateNamespaceCommand createAppCommand);

    /**
     * 查询集群命名空间
     *
     * @param namespaceQuery 查询集群命名空间
     * @return 命名空间集合
     */
    List<NamespaceDTO> listNamespace(NamespaceQuery namespaceQuery);

}

package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.namespace.CreateNamespaceCommand;
import com.rany.cake.devops.base.api.dto.NamespaceDTO;
import com.rany.cake.devops.base.api.query.NamespaceQuery;
import com.rany.cake.devops.base.api.service.NamespaceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 命名空间
 */
@RestController
@RequestMapping("/api/devops/namespace")
public class NamespaceController {

    @Resource
    private NamespaceService namespaceService;

    @PostMapping("/create")
    public PojoResult<String> createApp(CreateNamespaceCommand createNamespaceCommand) {
        return PojoResult.succeed(namespaceService.createNamespace(createNamespaceCommand));
    }

    @PostMapping("/listAll")
    public ListResult<NamespaceDTO> listAll(NamespaceQuery namespaceQuery) {
        return ListResult.succeed(namespaceService.listNamespace(namespaceQuery));
    }
}

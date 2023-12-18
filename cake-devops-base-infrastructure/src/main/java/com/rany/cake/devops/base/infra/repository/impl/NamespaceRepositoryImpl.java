package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import com.rany.cake.devops.base.infra.convertor.NamespaceDataConvertor;
import com.rany.cake.devops.base.infra.dao.NamespaceDao;
import com.rany.cake.devops.base.infra.mapper.NamespacePOMapper;
import com.rany.cake.devops.base.infra.po.NamespacePO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 命名空间
 *
 * @author zhongshengwang
 * @description 命名空间
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class NamespaceRepositoryImpl implements NameSpaceRepository {

    private final NamespacePOMapper namespacePOMapper;
    private final NamespaceDao namespaceDao;
    private final NamespaceDataConvertor namespaceDataConvertor;

    @Override
    public Namespace find(@NotNull NamespaceId namespaceId) {
        NamespacePO namespacePO = namespaceDao.selectByNamespaceId(namespaceId.getNamespaceId());
        return namespaceDataConvertor.targetToSource(namespacePO);
    }

    @Override
    public void remove(@NotNull Namespace namespace) {
        NamespacePO namespacePO = namespaceDataConvertor.sourceToTarget(namespace);
        namespacePO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        namespacePOMapper.updateByPrimaryKey(namespacePO);
    }

    @Override
    public void save(@NotNull Namespace namespace) {
        namespaceDao.save(namespace);
    }

    @Override
    public int update(Namespace namespace) {
        return namespaceDao.update(namespace);
    }

    @Override
    public List<Namespace> listNamespace(ClusterId clusterId) {
        return namespaceDao.listNamespace(clusterId.getClusterId());
    }
}

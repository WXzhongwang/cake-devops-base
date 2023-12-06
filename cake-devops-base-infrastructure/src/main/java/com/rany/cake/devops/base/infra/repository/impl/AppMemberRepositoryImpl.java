package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.AppMemberRepository;
import com.rany.cake.devops.base.infra.convertor.AppMemberDataConvertor;
import com.rany.cake.devops.base.infra.dao.AppMemberDao;
import com.rany.cake.devops.base.infra.mapper.AppMemberPOMapper;
import com.rany.cake.devops.base.infra.po.AppMemberPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * 应用成员
 *
 * @author zhongshengwang
 * @description 应用成员
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class AppMemberRepositoryImpl implements AppMemberRepository {

    private final AppMemberPOMapper appMemberPOMapper;
    private final AppMemberDao appMemberDao;
    private final AppMemberDataConvertor appMemberDataConvertor;

    @Override
    public AppMember find(@NotNull MemberId memberId) {
        AppMemberPO memberPO = appMemberPOMapper.selectByPrimaryKey(memberId.getId());
        return appMemberDataConvertor.targetToSource(memberPO);
    }

    @Override
    public void remove(@NotNull AppMember appMember) {
        AppMemberPO appMemberPO = appMemberDataConvertor.sourceToTarget(appMember);
        appMemberPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        appMemberPOMapper.updateByPrimaryKey(appMemberPO);
    }

    @Override
    public void save(@NotNull AppMember appMember) {
        appMemberDao.saveUpdate(appMember);
    }

    @Override
    public AppMember findByAccountId(Long accountId) {
        AppMemberPO appMemberPO = appMemberDao.selectByAccountId(accountId);
        if (appMemberPO == null) {
            return null;
        }
        return appMemberDataConvertor.targetToSource(appMemberPO);
    }

    @Override
    public void saveUpdate(AppMember appMember) {
        appMemberDao.saveUpdate(appMember);
    }
}

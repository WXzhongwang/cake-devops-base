package com.rany.cake.devops.base.domain.repository.impl;

import com.rany.cake.devops.base.dao.AppMemberDao;
import com.rany.cake.devops.base.dao.mapper.AppMemberPOMapper;
import com.rany.cake.devops.base.domain.aggegrate.AppMember;
import com.rany.cake.devops.base.domain.convertor.AppMemberDataConvertor;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.AppMemberRepository;
import com.rany.cake.devops.base.po.AppMemberPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
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
        return null;
    }

    @Override
    public void remove(@NotNull AppMember appMember) {

    }

    @Override
    public void save(@NotNull AppMember appMember) {

    }

    @Override
    public AppMember findByAccountId(Long accountId) {
        AppMemberPO appMemberPO = appMemberDao.selectByAccountId(accountId);
        return appMemberDataConvertor.targetToSource(appMemberPO);
    }
}

package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.AppMemberRepository;
import com.rany.cake.devops.base.domain.repository.param.AppMemberPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.AppMemberDataConvertor;
import com.rany.cake.devops.base.infra.dao.AppMemberDao;
import com.rany.cake.devops.base.infra.mapper.AppMemberPOMapper;
import com.rany.cake.devops.base.infra.po.AppMemberPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

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
        AppMemberPO memberPO = appMemberDao.selectByMemberId(memberId.getMemberId());
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
    public AppMember findByAccountId(String accountId) {
        AppMemberPO appMemberPO = appMemberDao.selectByAccountId(accountId);
        if (appMemberPO == null) {
            return null;
        }
        return appMemberDataConvertor.targetToSource(appMemberPO);
    }

    @Override
    public AppMember findByAppAccountId(String accountId, String appId) {
        AppMemberPO appMemberPO = appMemberDao.selectById(accountId, appId);
        if (appMemberPO == null) {
            return null;
        }
        return appMemberDataConvertor.targetToSource(appMemberPO);
    }

    @Override
    public AppMember findById(String accountId) {
        AppMemberPO appMemberPO = appMemberDao.selectByAccountId(accountId);
        if (appMemberPO == null) {
            return null;
        }
        return appMemberDataConvertor.targetToSource(appMemberPO);
    }

    @Override
    public List<AppMember> findByAppId(AppMemberPageQueryParam param) {
        List<AppMemberPO> appMemberPOList = appMemberDao.selectByAppId(param);
        return appMemberDataConvertor.targetToSource(appMemberPOList);
    }

    @Override
    @PagingQuery
    public Page<AppMember> pageByAppId(AppMemberPageQueryParam appMemberPageQueryParam) {
        List<AppMemberPO> appMemberPOList = appMemberDao.selectByAppId(appMemberPageQueryParam);
        PageInfo<AppMemberPO> pageInfo = new PageInfo<>(appMemberPOList);
        appMemberDataConvertor.targetToSource(appMemberPOList);
        List<AppMember> members = appMemberDataConvertor.targetToSource(appMemberPOList);
        return PageUtils.build(pageInfo, members);
    }

    @Override
    public void saveUpdate(AppMember appMember) {
        appMemberDao.saveUpdate(appMember);
    }

    @Override
    public void update(AppMember appMember) {
        appMemberDao.updateByMemberId(appMember);
    }
}

package com.rany.cake.devops.base.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.AppMemberRepository;
import com.rany.cake.devops.base.domain.repository.param.AppMemberPageQueryParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 成员
 *
 * @author zhongshengwang
 * @description 成员
 * @date 2023/1/15 16:01
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class AppMemberDomainService {

    private final AppMemberRepository appMemberRepository;

    public AppMember findByMemberId(String memberId) {
        return appMemberRepository.find(new MemberId(memberId));
    }

    public void update(AppMember appMember) {
        appMemberRepository.update(appMember);
    }

    public void saveUpdate(AppMember appMember) {
        appMemberRepository.saveUpdate(appMember);
    }

    public AppMember findByAccountId(String accountId) {
        return appMemberRepository.findByAccountId(accountId);
    }

    public List<AppMember> findByAppId(AppMemberPageQueryParam param) {
        return appMemberRepository.findByAppId(param);
    }

    public Page<AppMember> pageByAppId(AppMemberPageQueryParam param) {
        return appMemberRepository.pageByAppId(param);
    }
}

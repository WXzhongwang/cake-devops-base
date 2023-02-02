package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggegrate.AppMember;
import com.rany.cake.devops.base.domain.repository.AppMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 16:01
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class AppMemberDomainService {

    private final AppMemberRepository appMemberRepository;

    public AppMember findByAccountId(Long accountId) {
        return appMemberRepository.findByAccountId(accountId);
    }
}

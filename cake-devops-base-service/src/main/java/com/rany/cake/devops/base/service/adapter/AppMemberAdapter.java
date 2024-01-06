package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.api.query.AppMemberPageQuery;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.repository.param.AppMemberPageQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import com.rany.uic.common.dto.account.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 成员
 *
 * @author zhongshengwang
 * @description 应用
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface AppMemberAdapter extends BaseConvertor<AppMember, AppMemberDTO> {


    @Mapping(source = "memberId.memberId", target = "memberId")
    AppMemberDTO sourceToTarget(AppMember appMember);

    @Mapping(source = "memberId", target = "memberId.memberId")
    AppMember targetToSource(AppMemberDTO appMemberDTO);

    AppAccountDTO toDTO(AccountDTO accountDTO);

    List<AppAccountDTO> toDTO(List<AccountDTO> accountDTO);

    AppMemberPageQueryParam toParam(AppMemberPageQuery appMemberPageQuery);
}

package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PageResult;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.query.MemberPageQuery;

/**
 * 应用成员服务
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface AppMemberService {


    PageResult<AppAccountDTO> pageQueryMember(MemberPageQuery memberPageQuery);
}

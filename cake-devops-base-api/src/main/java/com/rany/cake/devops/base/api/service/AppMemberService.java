package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.member.AddAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.DeleteAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.UpdateAppMemberCommand;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.api.query.app.AppMemberPageQuery;
import com.rany.cake.devops.base.api.query.member.MemberPageQuery;

/**
 * 应用成员服务
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface AppMemberService {


    Page<AppAccountDTO> pageQueryMember(MemberPageQuery memberPageQuery);

    Page<AppMemberDTO> pageAppMembers(AppMemberPageQuery appMemberPageQuery);

    Boolean updateMember(UpdateAppMemberCommand updateAppMemberCommand);

    Boolean addMember(AddAppMemberCommand addAppMemberCommand);

    Boolean deleteMember(DeleteAppMemberCommand deleteAppMemberCommand);
}

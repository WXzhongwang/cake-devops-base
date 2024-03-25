package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.query.MemberPageQuery;
import com.rany.cake.devops.base.api.service.AppMemberService;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.configuration.SsoConstants;
import com.rany.cake.dingtalk.sdk.utils.SsoTokenLoginHelper;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/devops/user")
public class UserController {
    @Resource
    private AppMemberService appMemberService;

    @GetMapping("/getUser")
    public PojoResult<SsoUser> getUser(HttpServletRequest request) {
        String token = request.getHeader(SsoConstants.TOKEN_AUTH_HEADER);
        if (StringUtils.isNotEmpty(token)) {
            return PojoResult.succeed(SsoTokenLoginHelper.getStorageUser(token));
        }
        return PojoResult.succeed(SsoUtil.getCurrentUser(request));
    }

    @PostMapping("/queryMembers")
    public PageResult<AppAccountDTO> getUser(MemberPageQuery memberPageQuery) {
        return PageResult.succeed(appMemberService.pageQueryMember(memberPageQuery));
    }
}

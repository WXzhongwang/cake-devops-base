package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.member.MemberPageQuery;
import com.rany.cake.devops.base.api.service.AppMemberService;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.configuration.SsoConstants;
import com.rany.cake.dingtalk.sdk.utils.SsoTokenLoginHelper;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import com.rany.cake.dingtalk.starter.annotation.CurrentUser;
import com.rany.ops.api.facade.grant.RbacQueryFacade;
import com.rany.ops.api.query.grant.UserRoleMenuPermissionQuery;
import com.rany.ops.common.dto.application.UserRoleMenuDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhongshengwang
 */
@RestController
@RequestMapping("/api/devops/user")
public class UserController {
    @Resource
    private AppMemberService appMemberService;
    @Resource
    private RbacQueryFacade rbacQueryFacade;
    @Resource
    private AppConfig appConfig;

    @GetMapping("/current")

    public PojoResult<SsoUser> getUser(HttpServletRequest request) {
        String token = request.getHeader(SsoConstants.TOKEN_AUTH_HEADER);
        if (StringUtils.isNotEmpty(token)) {
            return PojoResult.succeed(SsoTokenLoginHelper.getStorageUser(token));
        }
        return PojoResult.succeed(SsoUtil.getCurrentUser(request));
    }

    @PostMapping("/query-members")
    public PageResult<AppAccountDTO> getUser(MemberPageQuery memberPageQuery) {
        return PageResult.succeed(appMemberService.pageQueryMember(memberPageQuery));
    }

    @PostMapping("/menu")
    public PojoResult<UserRoleMenuDTO> getUser(@CurrentUser SsoUser ssoUser) {
        UserRoleMenuPermissionQuery userRoleMenuPermissionQuery = new UserRoleMenuPermissionQuery();
        userRoleMenuPermissionQuery.setAccountId(Long.valueOf(ssoUser.getUserId()));
        userRoleMenuPermissionQuery.setAppCode(Constants.CAKE_DEVOPS_ACL_APP_CODE);
        UserRoleMenuDTO userRbacModel = rbacQueryFacade.getUserRbacModel(userRoleMenuPermissionQuery);
        if (userRbacModel == null) {
            throw new BusinessException(DevOpsErrorMessage.USER_MENU_INFO_ERROR);
        }
        return PojoResult.succeed(userRbacModel);
    }
}

package com.rany.cake.devops.base.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.repository.AppMemberRepository;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.param.AppQueryParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AppDomainService
 *
 * @author zhongshengwang
 * @description appDomainService
 * @date 2023/1/28 20:59
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class AppDomainService {

    private final AppRepository appRepository;
    private final AppMemberRepository appMemberRepository;

    /**
     * 创建应用
     *
     * @param app
     */

    public void save(App app) {
        appRepository.save(app);
        List<AppMember> appMembers = app.getAppMembers();
        for (AppMember appMember : appMembers) {
            if (appMember.getGmtCreate() == null) {
                appMember.setGmtCreate(app.getGmtCreate());
                appMember.setIsDeleted(DeleteStatusEnum.NO.getValue());
            } else {
                appMember.setGmtModified(app.getGmtCreate());
            }
            appMemberRepository.saveUpdate(appMember);
        }
    }

    public void createEnv(AppEnv appEnv) {
        appRepository.saveAppEnv(appEnv);
    }

    public void update(App app) {
        appRepository.update(app);
    }

    public App getApp(AppId appId) {
        return appRepository.find(appId);
    }

    public List<AppEnv> listAppEnv(AppId appId) {
        return appRepository.listAppEnv(appId.getId());
    }

    public Page<App> pageApp(AppQueryParam appQueryParam) {
        return appRepository.pageApp(appQueryParam);
    }
}

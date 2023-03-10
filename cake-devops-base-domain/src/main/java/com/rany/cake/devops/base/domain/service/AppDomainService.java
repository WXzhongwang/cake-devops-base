package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.repository.AppMemberRepository;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.uic.common.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
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
    public void createApp(App app) {
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
}

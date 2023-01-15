package com.rany.cake.devops.base.domain.aggegrate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.api.enums.CodeLanguageEnum;
import com.rany.cake.devops.base.api.enums.DevelopMode;
import com.rany.cake.devops.base.domain.entity.AppExtend;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import lombok.*;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/12 22:48
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class App extends BaseAggregateRoot implements IAggregate<AppId> {

    /**
     * 主键
     */
    private AppId id;

    /**
     * 应用名
     */
    private AppName appName;

    /**
     * 唯一标识
     */
    private String uniqueNo;
    /**
     * 应用描述
     */
    private String description;
    private CodeRepository codeRepository;
    private CodeLanguageEnum language;
    private AppExtend appExtend;
    private DevelopMode developMode;
    /**
     * 应用成员
     */
    private List<AppMember> appMembers;
    private Long owner;

    public App(AppId appId, AppName appName, Long owner) {
        this.id = appId;
        this.owner = owner;
        this.appName = appName;
    }

    public void sava() {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
    }
}

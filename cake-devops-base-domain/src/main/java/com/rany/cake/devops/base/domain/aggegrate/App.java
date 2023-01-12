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

    private String uniqueNo;
    /**
     * 应用描述
     */
    private String description;
    private CodeRepository codeRepository;
    private CodeLanguageEnum codeLanguageEnum;
    private AppExtend appExtend;
    private DevelopMode developMode;
    private Long owner;

    public App(AppId appId, AppName appName) {
        this.id = appId;
    }

    public void sava() {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
    }
}

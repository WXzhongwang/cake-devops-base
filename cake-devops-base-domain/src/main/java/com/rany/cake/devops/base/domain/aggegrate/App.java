package com.rany.cake.devops.base.domain.aggegrate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.api.enums.CodeLanguageEnum;
import com.rany.cake.devops.base.api.enums.DevelopMode;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.entity.AppExtend;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.domain.valueobject.BusinessOwnership;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import com.rany.cake.devops.base.domain.valueobject.VolumeMount;
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
    /**
     * 应用环境
     */
    private List<AppEnv> appEnvList;
    /**
     * 拥有者
     */
    private Long owner;
    /**
     * 健康检查
     * 健康检查路径，端口后的uri，如：/health
     * 若未配置，将使用tcp检查
     */
    private String healthCheck;

    /**
     * 业务归属
     */
    private BusinessOwnership businessOwnership;

    /**
     * 挂载
     */
    private List<VolumeMount> volumeMounts;

    public App(AppId appId, AppName appName, Long owner, String description, CodeRepository codeRepository,
               CodeLanguageEnum language,
               DevelopMode developMode) {
        this.id = appId;
        this.owner = owner;
        this.appName = appName;
        this.description = description;
        this.codeRepository = codeRepository;
        this.language = language;
        this.developMode = developMode;
    }

    public void sava() {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
    }
}

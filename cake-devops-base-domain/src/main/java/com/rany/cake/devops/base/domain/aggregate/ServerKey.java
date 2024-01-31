package com.rany.cake.devops.base.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ServerKeyId;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServerKey extends BaseAggregateRoot implements IAggregate<ServerKeyId> {

    private ServerKeyId serverKeyId;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 0普通账户/1管理员
     */
    private Integer accountType;

    /**
     * 默认ssh
     */
    private String protocol;

    /**
     * 是否活跃可用
     */
    private Boolean active;
    /**
     * 凭据内容
     */
    private String credential;
    /**
     * publicKey
     */
    private String publicKey;
    /**
     * 密码短语
     */
    private String passphrase;

    public ServerKey(ServerKeyId serverKeyId) {
        this.serverKeyId = serverKeyId;
    }

    public void init() {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }

    public Boolean delete() {
        this.gmtModified = DateUtil.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }

    public Boolean modify() {
        this.gmtModified = DateUtil.date();
        return Boolean.TRUE;
    }

    @Override
    public ServerKeyId getBizID() {
        return serverKeyId;
    }
}

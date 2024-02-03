package com.rany.cake.devops.base.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServerKey extends BaseEntity<Long> {

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
}

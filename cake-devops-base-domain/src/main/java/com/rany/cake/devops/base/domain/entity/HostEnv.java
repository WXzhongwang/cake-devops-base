package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.toolkit.lang.time.Dates;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 主机环境变量
 *
 * @author zhongshengwang
 * @description 主机环境变量
 * @date 2023/8/24 22:03
 * @email 18668485565163.com
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class HostEnv extends BaseEntity<Long> {

    private String hostId;
    private String attrKey;
    private String attrValue;
    private String description;


    public void init(String user) {
        this.creator = user;
        this.gmtCreate = Dates.date();
        this.gmtModified = Dates.date();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
    }

    public Boolean delete(String user) {
        this.modifier = user;
        this.gmtModified = Dates.date();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
        return Boolean.TRUE;
    }

    public Boolean modify(String user) {
        this.modifier = user;
        this.gmtModified = Dates.date();
        return Boolean.TRUE;
    }
}

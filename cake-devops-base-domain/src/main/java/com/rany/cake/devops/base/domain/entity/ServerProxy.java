package com.rany.cake.devops.base.domain.entity;


import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.toolkit.lang.time.Dates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServerProxy extends BaseEntity<Long> {
    private String proxyHost;
    private Integer proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private Integer proxyType;
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

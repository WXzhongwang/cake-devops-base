package com.rany.cake.devops.base.domain.entity;


import com.cake.framework.common.base.BaseEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ServerProxy extends BaseEntity<Long> {
    private String proxyHost;
    private String proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private Integer proxyType;
}

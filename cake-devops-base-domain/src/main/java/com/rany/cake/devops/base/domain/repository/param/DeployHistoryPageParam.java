package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class DeployHistoryPageParam extends BasePageParam {
    private String envId;
    private String appId;
}
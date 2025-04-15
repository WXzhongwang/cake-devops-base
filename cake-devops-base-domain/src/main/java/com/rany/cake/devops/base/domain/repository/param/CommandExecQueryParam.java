package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommandExecQueryParam extends BasePageParam {
    private String hostId;
    private Integer execStatus;
    private List<Integer> execStatusList;
    private Integer execType;
    private String accountId;
    private List<String> hostIds;
}

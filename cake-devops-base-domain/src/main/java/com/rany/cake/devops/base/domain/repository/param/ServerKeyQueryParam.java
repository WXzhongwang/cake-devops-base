package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerKeyQueryParam extends BasePageParam {
    private String hostId;
    private String displayName;
    private String accountType;
}

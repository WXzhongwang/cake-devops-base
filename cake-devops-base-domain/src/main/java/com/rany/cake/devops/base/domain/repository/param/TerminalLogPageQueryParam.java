package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class TerminalLogPageQueryParam extends BasePageParam {

    private Long userId;

    private String hostId;

    private String accessToken;

    private Date connectedTimeStart;

    private Date connectedTimeEnd;

    private Date disconnectedTimeStart;

    private Date disconnectedTimeEnd;

    private Integer closeCode;
}

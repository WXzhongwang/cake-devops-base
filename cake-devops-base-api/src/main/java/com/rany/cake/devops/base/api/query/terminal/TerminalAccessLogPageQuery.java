package com.rany.cake.devops.base.api.query.terminal;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class TerminalAccessLogPageQuery extends BasePageQuery {
    private Long userId;

    private String hostId;

    private String accessToken;

    private Date connectedTimeStart;

    private Date connectedTimeEnd;

    private Date disconnectedTimeStart;

    private Date disconnectedTimeEnd;

    private Integer closeCode;
}

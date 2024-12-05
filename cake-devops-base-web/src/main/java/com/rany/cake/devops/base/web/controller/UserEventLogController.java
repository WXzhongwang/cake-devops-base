package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.rany.cake.devops.base.api.dto.log.UserEventLogDTO;
import com.rany.cake.devops.base.api.query.log.UserEventLogPageQuery;
import com.rany.cake.devops.base.api.service.UserEventLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/devops/logs")
public class UserEventLogController {
    @Resource
    private UserEventLogService userEventLogService;

    @PostMapping("/query-logs")
    public PageResult<UserEventLogDTO> page(@RequestBody UserEventLogPageQuery userEventLogPageQuery) {
        return PageResult.succeed(userEventLogService.pageQueryLog(userEventLogPageQuery));
    }
}

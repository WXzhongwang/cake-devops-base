package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.MapResult;
import com.cake.framework.common.response.PageResult;
import com.rany.cake.devops.base.api.common.enums.EventClassify;
import com.rany.cake.devops.base.api.common.enums.EventType;
import com.rany.cake.devops.base.api.dto.log.UserEventLogDTO;
import com.rany.cake.devops.base.api.query.log.UserEventLogPageQuery;
import com.rany.cake.devops.base.api.service.UserEventLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/devops/logs")
public class UserEventLogController {
    @Resource
    private UserEventLogService userEventLogService;

    @GetMapping("/get-classify")
    public MapResult<Integer, String> getEventClassify() {
        EventClassify[] eventClassifies = EventClassify.values();
        HashMap<Integer, String> data = new HashMap<>();
        for (EventClassify eventClassify : eventClassifies) {
            data.put(eventClassify.getClassify(), eventClassify.getLabel());
        }
        return MapResult.succeed(data);
    }

    @GetMapping("/get-event-type")
    public MapResult<Integer, String> getEventType(@RequestParam("eventClassify") Integer eventClassify) {
        List<EventType> eventTypes = EventType.ofClassify(eventClassify);
        HashMap<Integer, String> data = new HashMap<>();
        for (EventType eventType : eventTypes) {
            data.put(eventType.getType(), eventType.getLabel());
        }
        return MapResult.succeed(data);
    }


    @PostMapping("/query-logs")
    public PageResult<UserEventLogDTO> page(@RequestBody UserEventLogPageQuery userEventLogPageQuery) {
        return PageResult.succeed(userEventLogService.pageQueryLog(userEventLogPageQuery));
    }
}

package com.rany.cake.devops.base.service.app;

import com.alibaba.fastjson.JSON;
import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.common.enums.EventType;
import com.rany.cake.devops.base.api.dto.log.UserEventLogDTO;
import com.rany.cake.devops.base.api.query.log.UserEventLogPageQuery;
import com.rany.cake.devops.base.api.service.UserEventLogService;
import com.rany.cake.devops.base.domain.entity.UserEventLog;
import com.rany.cake.devops.base.domain.repository.UserEventLogRepository;
import com.rany.cake.devops.base.domain.repository.param.UserEventLogQueryPageParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.UserEventLogDataAdapter;
import com.rany.cake.devops.base.service.base.EventParamsHolder;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.EventKeys;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.toolkit.lang.collect.MutableMap;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * UserEventLogServiceImpl
 *
 * @author zhongshengwang
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserEventLogServiceImpl implements UserEventLogService {

    private final UserEventLogRepository userEventLogRepository;
    private final UserEventLogDataAdapter userEventLogDataAdapter;

    @Override
    public void recordLog(EventType eventType, boolean isSuccess) {
        // 获取以及移除参数
        MutableMap<String, Object> paramsMap = EventParamsHolder.get();
        String userId = paramsMap.getString(EventKeys.INNER_USER_ID);
        if (Strings.isEmpty(userId)) {
            log.info("unknown user");
            return;
        }

        String template = paramsMap.getString(EventKeys.INNER_TEMPLATE, eventType.getTemplate());
        // 设置对象
        UserEventLog log = new UserEventLog();
        log.setUserId(paramsMap.getString(EventKeys.INNER_USER_ID));
        log.setUsername(paramsMap.getString(EventKeys.INNER_USER_NAME));
        log.setEventClassify(eventType.getClassify().getClassify());
        log.setEventType(eventType.getType());
        log.setLogInfo(Strings.format(template, paramsMap));
        log.setParamsJson(JSON.toJSONString(paramsMap));
        log.setExecResult(isSuccess ? Const.ENABLE : Const.DISABLE);
        log.setGmtCreate(new Date());
        log.setGmtModified(new Date());
        log.setCreator(userId);
        log.setModifier(userId);
        log.setGmtModified(new Date());
        log.setIsDeleted(DeleteStatusEnum.NO.getValue());
        // 插入
        userEventLogRepository.save(log);
    }

    @Override
    public Page<UserEventLogDTO> pageQueryLog(UserEventLogPageQuery query) {
        UserEventLogQueryPageParam userEventLogQueryPageParam = userEventLogDataAdapter.convertParam(query);
        Page<UserEventLog> page = userEventLogRepository.pageQuery(userEventLogQueryPageParam);
        Collection<UserEventLog> items = page.getItems();
        List<UserEventLogDTO> events = userEventLogDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageUtils.build(page, events);
    }
}

package com.rany.cake.devops.base.service.app;

import com.rany.cake.devops.base.api.service.UserEventLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * UserEventLogServiceImpl
 *
 * @author zhongshengwang
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserEventLogServiceImpl implements UserEventLogService {
    @Override
    public void recordLog(Integer eventType, boolean isSuccess) {

    }
}

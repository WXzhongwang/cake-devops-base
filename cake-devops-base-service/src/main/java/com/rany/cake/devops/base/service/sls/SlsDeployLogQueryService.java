package com.rany.cake.devops.base.service.sls;

import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.QueriedLog;
import com.rany.cake.devops.base.api.dto.DeployLogDTO;
import com.rany.cake.devops.base.domain.base.SlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SlsDeployLogQueryService {

    @Autowired
    private SlsConfig slsConfig;
    private SlsLogQuery slsLogQuery;

    @PostConstruct
    public void init() {
        slsLogQuery = new SlsLogQuery(slsConfig.getEndpoint(), slsConfig.getAccessKeyId(), slsConfig.getAccessKeySecret(), slsConfig.getProject(), slsConfig.getLogstore());
    }

    @PreDestroy
    public void destroy() {
        slsLogQuery.close();
    }

    public List<DeployLogDTO> queryLogs(String pipeKey, long from, long to) {
        List<QueriedLog> logs = null;
        List<DeployLogDTO> queriedLogs = new ArrayList<>();
        try {
            logs = slsLogQuery.queryLogsByPipeKey(pipeKey, from, to);
            for (QueriedLog log : logs) {
                DeployLogDTO deployLogDTO = new DeployLogDTO();
                deployLogDTO.setPipeKey(pipeKey);
                for (LogContent mContent : log.mLogItem.mContents) {
                    if (StringUtils.equals(mContent.mKey, "message")) {
                        deployLogDTO.setMessage(mContent.mValue);
                    }
                    if (StringUtils.equals(mContent.mKey, "level")) {
                        deployLogDTO.setLevel(mContent.mValue);
                    }
                    if (StringUtils.equals(mContent.mKey, "time")) {
                        deployLogDTO.setTime(mContent.mValue);
                    }
                    if (StringUtils.equals(mContent.mKey, "thread")) {
                        deployLogDTO.setThread(mContent.mValue);
                    }
                    if (StringUtils.equals(mContent.mKey, "location")) {
                        deployLogDTO.setLocation(mContent.mValue);
                    }
                }
                queriedLogs.add(deployLogDTO);
            }
        } catch (Exception e) {
            log.error("查询日志异常", e);
            return Collections.emptyList();
        }
        return queriedLogs;
    }

}

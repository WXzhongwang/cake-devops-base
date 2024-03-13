package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.rany.cake.devops.base.api.dto.HostAlarmHistoryDTO;
import com.rany.cake.devops.base.api.query.HostAlarmHistoryPageQuery;
import com.rany.cake.devops.base.api.service.HostAlarmService;
import com.rany.cake.devops.base.domain.entity.HostAlarmHistory;
import com.rany.cake.devops.base.domain.repository.HostAlarmHistoryRepository;
import com.rany.cake.devops.base.domain.repository.param.HostAlarmHistoryPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostAlarmHistoryDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@ShenyuService("/host-alarm/**")
@Slf4j
@AllArgsConstructor
public class HostAlarmRemoteService implements HostAlarmService {
    private final HostAlarmHistoryRepository hostAlarmHistoryRepository;
    private final HostAlarmHistoryDataAdapter hostAlarmHistoryDataAdapter;

    @Override
    public PageResult<HostAlarmHistoryDTO> pageHistory(HostAlarmHistoryPageQuery hostAlarmHistoryPageQuery) {
        HostAlarmHistoryPageQueryParam queryParam = hostAlarmHistoryDataAdapter.convertParam(hostAlarmHistoryPageQuery);
        Page<HostAlarmHistory> page = hostAlarmHistoryRepository.pageQueryAlarmHistory(queryParam);
        Collection<HostAlarmHistory> items = page.getItems();
        List<HostAlarmHistoryDTO> alarmHistoryDTOList = hostAlarmHistoryDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageResult.succeed(PageUtils.build(page, alarmHistoryDTOList));
    }
}

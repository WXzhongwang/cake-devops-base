package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.host.alarm.HostAlarmCommand;
import com.rany.cake.devops.base.api.dto.HostAlarmHistoryDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.alarm.HostAlarmHistoryPageQuery;
import com.rany.cake.devops.base.api.service.HostAlarmService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.HostAlarmHistory;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostAlarmHistoryRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.param.HostAlarmHistoryPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostAlarmHistoryDataAdapter;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.handler.alarm.MachineAlarmContext;
import com.rany.cake.devops.base.service.handler.alarm.MachineAlarmExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhongshengwang
 */
@Service
@Slf4j
@AllArgsConstructor
public class HostAlarmServiceImpl implements HostAlarmService {
    private final HostRepository hostRepository;
    private final HostAlarmHistoryRepository hostAlarmHistoryRepository;
    private final HostAlarmHistoryDataAdapter hostAlarmHistoryDataAdapter;
    private final MachineAlarmExecutor machineAlarmExecutor;

    @Override
    public Page<HostAlarmHistoryDTO> pageHistory(HostAlarmHistoryPageQuery hostAlarmHistoryPageQuery) {
        HostAlarmHistoryPageQueryParam queryParam = hostAlarmHistoryDataAdapter.convertParam(hostAlarmHistoryPageQuery);
        Page<HostAlarmHistory> page = hostAlarmHistoryRepository.pageQueryAlarmHistory(queryParam);
        Collection<HostAlarmHistory> items = page.getItems();
        List<HostAlarmHistoryDTO> alarmHistoryDTOList = hostAlarmHistoryDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageUtils.build(page, alarmHistoryDTOList);
    }

    @Override
    public void triggerHostAlarm(HostAlarmCommand command) {
        Host host = hostRepository.find(new HostId(command.getHostId()));
        if (host == null) {
            throw new BusinessException(DevOpsErrorMessage.HOST_NOT_FOUND);
        }

        HostAlarmHistory history = new HostAlarmHistory();
        history.setHostId(command.getHostId());
        history.setAlarmType(command.getType());
        history.setAlarmTime(command.getAlarmTime());
        history.setAlarmValue(command.getAlarmValue());
        history.init(Constants.SYSTEM);
        hostAlarmHistoryRepository.save(history);

        // 执行通知操作
        MachineAlarmContext context = new MachineAlarmContext();
        context.setHostId(command.getHostId());
        context.setHostName(host.getHostName());
        context.setServerAddr(host.getServerAddr());
        context.setAlarmType(command.getType());
        context.setAlarmValue(command.getAlarmValue());
        context.setAlarmTime(command.getAlarmTime());
        machineAlarmExecutor.execute(context);
    }
}

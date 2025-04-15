package com.rany.cake.devops.base.service.adapter.conversions;

import com.rany.cake.devops.base.api.dto.CommandExecDTO;
import com.rany.cake.devops.base.api.dto.CommandExecStatusDTO;
import com.rany.cake.devops.base.infra.po.CommandExecPO;
import com.rany.cake.devops.base.service.utils.Utils;
import com.rany.cake.toolkit.lang.convert.TypeStore;
import com.rany.cake.toolkit.lang.time.Dates;

import java.util.Date;
import java.util.Optional;

/**
 * 命令执行 对象转换器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/10 18:03
 */
public class CommandExecConversion {

    static {
        TypeStore.STORE.register(CommandExecPO.class, CommandExecStatusDTO.class, p -> {
            CommandExecStatusDTO vo = new CommandExecStatusDTO();
            vo.setId(p.getId());
            vo.setExitCode(p.getExitCode());
            vo.setStatus(p.getExecStatus());
            if (p.getStartDate() != null && p.getEndDate() != null) {
                vo.setUsed(p.getEndDate().getTime() - p.getStartDate().getTime());
                vo.setKeepTime(Utils.interval(vo.getUsed()));
            }
            return vo;
        });
    }

    static {
        TypeStore.STORE.register(CommandExecPO.class, CommandExecDTO.class, p -> {
            CommandExecDTO vo = new CommandExecDTO();
            Date startDate = p.getStartDate();
            Date endDate = p.getEndDate();
            Date createTime = p.getGmtCreate();
            vo.setId(p.getId());
            vo.setAccountId(p.getAccountId());
            vo.setUsername(p.getUserName());
            vo.setExecType(p.getExecType());
            vo.setExecStatus(p.getExecStatus());
            vo.setHostId(p.getHostId());
            vo.setExitCode(p.getExitCode());
            vo.setExecCommand(p.getExecCommand());
            vo.setDescription(p.getDescription());
            vo.setStartDate(startDate);
            vo.setEndDate(endDate);
            vo.setGmtCreate(createTime);
            Optional.ofNullable(startDate).map(Dates::ago).ifPresent(vo::setStartDateAgo);
            Optional.ofNullable(endDate).map(Dates::ago).ifPresent(vo::setEndDateAgo);
            Optional.ofNullable(createTime).map(Dates::ago).ifPresent(vo::setCreateTimeAgo);
            if (startDate != null && endDate != null) {
                vo.setUsed(endDate.getTime() - startDate.getTime());
                vo.setKeepTime(Utils.interval(vo.getUsed()));
            }
            return vo;
        });
    }
}

package com.rany.cake.devops.plugin.entity.vo;

import com.rany.cake.devops.plugin.entity.dto.SystemProcessDTO;
import com.rany.cake.devops.plugin.utils.Formats;
import com.rany.cake.toolkit.lang.convert.TypeStore;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.time.Dates;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统进程
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/6/30 17:44
 */
@Data
@ApiModel(value = "系统进程")
public class SystemProcessVO {

    @ApiModelProperty(value = "进程id")
    private Integer pid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "用户")
    private String user;

    @ApiModelProperty(value = "cpu使用率")
    private Double cpuUsage;

    @ApiModelProperty(value = "使用内存")
    private String memoryUsage;

    @ApiModelProperty(value = "句柄数")
    private Long openFile;

    @ApiModelProperty(value = "启用时长")
    private String uptime;

    @ApiModelProperty(value = "命令行")
    private String commandLine;

    static {
        TypeStore.STORE.register(SystemProcessDTO.class, SystemProcessVO.class, p -> {
            SystemProcessVO vo = new SystemProcessVO();
            vo.setPid(p.getPid());
            vo.setName(p.getName());
            vo.setUser(p.getUser());
            vo.setCpuUsage(Formats.roundToDouble(p.getCpuUsage()));
            vo.setMemoryUsage(Files1.getSize(p.getMemoryUsage()));
            vo.setOpenFile(p.getOpenFile());
            vo.setUptime(Formats.formatElapsedSecs(p.getUptime() / Dates.SECOND_STAMP));
            vo.setCommandLine(p.getCommandLine());
            return vo;
        });
    }

}

package com.rany.cake.devops.plugin.entity.vo;

import com.rany.cake.devops.plugin.entity.dto.DiskIoUsageDTO;
import com.rany.cake.toolkit.lang.convert.TypeStore;
import com.rany.cake.toolkit.lang.io.Files1;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 硬盘 IO 使用信息
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/6/30 18:46
 */
@Data
@ApiModel(value = "硬盘IO使用信息")
public class DiskIoUsageVO {

    @ApiModelProperty(value = "硬盘名称")
    private String model;

    @ApiModelProperty(value = "读取次数")
    private Long readCount;

    @ApiModelProperty(value = "读取大小")
    private String readSize;

    @ApiModelProperty(value = "写入次数")
    private Long writeCount;

    @ApiModelProperty(value = "写入大小")
    private String writeSize;

    static {
        TypeStore.STORE.register(DiskIoUsageDTO.class, DiskIoUsageVO.class, p -> {
            DiskIoUsageVO vo = new DiskIoUsageVO();
            vo.setModel(p.getModel());
            vo.setReadCount(p.getReadCount());
            vo.setReadSize(Files1.getSize(p.getReadBytes()));
            vo.setWriteCount(p.getWriteCount());
            vo.setWriteSize(Files1.getSize(p.getWriteBytes()));
            return vo;
        });
    }

}

package com.rany.cake.devops.plugin.entity.vo;

import com.rany.cake.devops.plugin.entity.dto.NetBandwidthDTO;
import com.rany.cake.devops.plugin.utils.Formats;
import com.rany.cake.toolkit.lang.constant.Const;
import com.rany.cake.toolkit.lang.convert.TypeStore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 网络带宽流量信息
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/6/30 18:41
 */
@Data
@ApiModel(value = "网络带宽流量信息")
public class NetBandwidthVO {

    @ApiModelProperty(value = "上行流量速率 mbp/s")
    private Double up;

    @ApiModelProperty(value = "下行流量速率 mbp/s")
    private Double down;

    static {
        TypeStore.STORE.register(NetBandwidthDTO.class, NetBandwidthVO.class, p -> {
            NetBandwidthVO vo = new NetBandwidthVO();
            vo.setUp(Formats.roundToDouble((double) p.getUp() / Const.MBP, 5));
            vo.setDown(Formats.roundToDouble((double) p.getDown() / Const.MBP, 5));
            return vo;
        });
    }

}

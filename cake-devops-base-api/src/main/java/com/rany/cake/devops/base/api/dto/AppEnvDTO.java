package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import com.rany.cake.devops.base.api.enums.AppEnvEnum;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:20
 * @email 18668485565163.com
 */
@Data
public class AppEnvDTO extends DTO {

    private Long clusterId;
    private AppEnvEnum envEnum;
    private String envName;
    private List<String> domains;
    private ResourceStrategyDTO resourceStrategyDTO;

    /**
     * 是否自动扩容
     */
    private Boolean autoScaling;
    /**
     * 是否开启部署 需要审批
     */
    private Boolean needApproval;
}

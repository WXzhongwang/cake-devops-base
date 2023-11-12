package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 应用环境
 *
 * @author zhongshengwang
 * @description 应用环境
 * @date 2023/1/28 20:20
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppEnvDTO extends DTO {

    private Long clusterId;
    private String envEnum;
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

package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * PodDTO
 *
 * @author zhongshengwang
 * @description PodInfoDTO
 * @date 2023/1/20 19:46
 * @email 18668485565163.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PodDTO extends DTO {

    private String name;
    private String namespace;
    private String podIp;
    private String phase;
    private String nodeName;
    private String startTime;
    private boolean isReady;

}

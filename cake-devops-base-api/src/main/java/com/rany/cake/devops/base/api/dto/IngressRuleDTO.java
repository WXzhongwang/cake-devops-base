package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/2/7 15:17
 * @slogon 找到银弹
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IngressRuleDTO extends DTO {
    private String host;
    private List<IngressPathDTO> paths;
}

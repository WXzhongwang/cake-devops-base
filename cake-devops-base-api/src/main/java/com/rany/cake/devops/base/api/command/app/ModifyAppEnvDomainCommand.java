package com.rany.cake.devops.base.api.command.app;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import com.rany.cake.devops.base.api.dto.IngressDTO;
import com.rany.cake.devops.base.api.dto.IngressRuleDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * domains
 *
 * @author zhongshengwang
 * @description domains
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyAppEnvDomainCommand extends BaseCommand {
    private String envId;

    private IngressDTO ingressDTO;

    public List<String> getDomains() {
        if (ingressDTO == null || ingressDTO.getRules() == null) {
            return null;
        }
        return ingressDTO.getRules().stream().map(IngressRuleDTO::getHost).collect(Collectors.toList());
    }
}

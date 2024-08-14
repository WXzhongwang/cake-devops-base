package com.rany.cake.devops.base.api.command.app;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    private List<String> domains;
}

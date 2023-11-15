package com.rany.cake.devops.base.api.command.release;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 立即发布
 *
 * @author zhongshengwang
 * @description 立即发布
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeployCommand extends BaseCommand {

    /**
     * 发布ID
     */
    private Long releaseId;
}

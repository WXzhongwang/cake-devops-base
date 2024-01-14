package com.rany.cake.devops.base.api.command.release;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加回滚发布
 *
 * @author zhongshengwang
 * @description 添加发布
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateRollbackReleaseCommand extends BaseCommand {

    /**
     * 应用ID
     */
    private String appId;
    /**
     * 环境ID
     */
    private String envId;
    /**
     * 回滚发布标识
     */
    private String rollback;
    /**
     * 回滚到哪一次发布
     */
    private String rollbackId;
}

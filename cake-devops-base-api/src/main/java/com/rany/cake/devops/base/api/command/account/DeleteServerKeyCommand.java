package com.rany.cake.devops.base.api.command.account;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 编辑服务账号
 *
 * @author zhongshengwang
 * @description 服务账号
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteServerKeyCommand extends BaseCommand {

    private Long serverKeyId;

}

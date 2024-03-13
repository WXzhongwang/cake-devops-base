package com.rany.cake.devops.base.api.command.message;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 已读站内信
 *
 * @author zhongshengwang
 * @description 已读站内信
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReadWebSideMessageCommand extends BaseCommand {

    private List<Long> messageIdList;
}

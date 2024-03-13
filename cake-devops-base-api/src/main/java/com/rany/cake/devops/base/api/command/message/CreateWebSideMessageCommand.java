package com.rany.cake.devops.base.api.command.message;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 创建站内信
 *
 * @author zhongshengwang
 * @description 创建站内信
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateWebSideMessageCommand extends BaseCommand {
    private Byte messageClassify;
    private Integer messageType;
    private Byte readStatus;
    private Long toUserId;
    private String toUserName;
    private String relId;
    private String sendMessage;
}

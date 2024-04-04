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
public class ModifyServerKeyCommand extends BaseCommand {

    /**
     * serverKeyId
     */
    private Long serverKeyId;

    /**
     * displayName
     */
    private String displayName;

    /**
     * 认证模式
     */
    private String authMode;
    /**
     * 0普通账户/1管理员
     */
    private Integer accountType;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 凭据内容
     */
    private String credential;
    /**
     * publicKey
     */
    private String publicKey;
    /**
     * 密码短语
     */
    private String passphrase;
    /**
     * 文件路径
     */
    private String keyPath;

    /**
     * base64
     */
    private String fileBase64;
}

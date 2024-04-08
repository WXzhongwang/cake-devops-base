package com.rany.cake.devops.base.api.command.key;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 密钥
 *
 * @author zhongshengwang
 * @description 密钥
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateServerKeyCommand extends BaseCommand {

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 0普通账户/1管理员
     */
    private Integer accountType;
    /**
     * 默认ssh
     */
    private String protocol;

    /**
     * 是否活跃
     */
    private String active;

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
     * 密钥文件存放路径
     */
    private String keyPath;

    private String fileBase64;
}

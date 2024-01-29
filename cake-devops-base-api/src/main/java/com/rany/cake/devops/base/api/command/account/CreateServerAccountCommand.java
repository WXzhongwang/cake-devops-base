package com.rany.cake.devops.base.api.command.account;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 服务账号
 *
 * @author zhongshengwang
 * @description 服务账号
 * @date 2022/12/30 22:00
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateServerAccountCommand extends BaseCommand {

    private String hostId;
    private String username;
    private String displayName;
    private String authMode;
    /**
     * 0普通账户/1管理员
     */
    private Integer accountType;
    private String protocol;

    private Boolean active;

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

}

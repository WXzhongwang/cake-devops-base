package com.rany.cake.devops.base.api.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 登陆绑定信息
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2024/5/18 23:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginBindDTO implements Serializable {

    private Long timestamp;

    private String loginIp;

}

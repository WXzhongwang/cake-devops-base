package com.rany.cake.devops.base.api.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/2 16:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("ALL")
public class UserDTO implements Serializable {

    private String userId;

    private String username;

    private String realName;

    private Long timestamp;

    private Long currentBindTimestamp;

    private String bindIp;

}

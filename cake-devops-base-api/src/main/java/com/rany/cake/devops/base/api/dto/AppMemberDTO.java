package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 应用成员
 *
 * @author zhongshengwang
 * @description 应用成员
 * @date 2023/1/28 20:20
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppMemberDTO extends DTO {

    private String memberId;
    private String accountId;
    private AppAccountDTO accountDTO;
    private List<String> roles;
}

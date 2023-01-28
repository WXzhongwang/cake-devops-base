package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:20
 * @email 18668485565163.com
 */
@Data
public class AppMemberDTO extends DTO {

    private List<Long> accountIds;
    private String role;
}

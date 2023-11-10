package com.rany.cake.devops.base.api.common.base;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础Command操作需集成该类型
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:29
 * @email 18668485565163.com
 */
@Getter
@Setter
public abstract class BaseCommand extends DTO {

    /**
     * 当前操作人
     */
    private String user;
}

package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * volume挂载
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/19 18:01
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VolumeMount extends BaseValueObject {

    /**
     * 数据卷名称
     */
    private String name;
    /**
     * 主机路径
     */
    private String path;
    /**
     * 挂载路径
     */
    private String mountPath;
}

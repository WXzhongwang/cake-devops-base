package com.rany.cake.devops.base.domain.entity.extend;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/1 21:06
 * @email 18668485565163.com
 */
@Data
public class JavaExtend implements Serializable {
    private String jdkVersion;
    private String mvnVersion;
}

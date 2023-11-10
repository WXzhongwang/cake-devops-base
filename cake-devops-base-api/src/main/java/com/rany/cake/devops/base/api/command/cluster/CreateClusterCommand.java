package com.rany.cake.devops.base.api.command.cluster;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 添加集群
 *
 * @author zhongshengwang
 * @description 添加集群
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateClusterCommand extends DTO {
    private String connectString;
    private String token;
    private String name;
    private String clusterType;
    private String env;
    private List<String> tags;
}

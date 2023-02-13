package com.rany.cake.devops.base.api.command;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
public class CreateClusterCommand extends DTO {
    private String connectString;
    private String token;
    private String name;
    private String clusterType;
    private String env;
    private List<String> tags;
}

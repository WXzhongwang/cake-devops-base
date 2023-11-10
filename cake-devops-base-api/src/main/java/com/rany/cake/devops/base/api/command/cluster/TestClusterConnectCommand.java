package com.rany.cake.devops.base.api.command.cluster;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试连通性
 *
 * @author zhongshengwang
 * @description 测试连通性
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestClusterConnectCommand extends DTO {
    private String connectString;
    private String token;
    private String clusterType;

    public TestClusterConnectCommand(String connectString, String token, String clusterType) {
        this.connectString = connectString;
        this.token = token;
        this.clusterType = clusterType;
    }
}

package com.rany.cake.devops.base.api.command;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:10
 * @email 18668485565163.com
 */
@Data
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

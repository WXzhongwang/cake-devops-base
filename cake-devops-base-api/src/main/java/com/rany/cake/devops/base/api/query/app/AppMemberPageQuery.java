package com.rany.cake.devops.base.api.query.app;

import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 应用人员查询
 *
 * @author zhongshengwang
 * @description 应用人员查询
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AppMemberPageQuery extends BasePageQuery {
    private String appId;
    private List<String> accountIds;
    // private String name;
}

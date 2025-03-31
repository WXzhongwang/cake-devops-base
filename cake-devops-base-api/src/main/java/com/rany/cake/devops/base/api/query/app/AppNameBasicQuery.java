package com.rany.cake.devops.base.api.query.app;

import com.rany.cake.devops.base.api.common.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 查询主机
 *
 * @author zhongshengwang
 * @description 查询主机
 * @date 2022/12/31 16:57
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppNameBasicQuery extends BaseQuery {

    private String appName;


}

package com.rany.cake.devops.base.api.query.release;


import com.rany.cake.devops.base.api.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加发布
 *
 * @author zhongshengwang
 * @description 添加发布
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReleasePageQuery extends BasePageQuery {

    /**
     * 应用ID
     */
    private String appId;
    /**
     * 环境ID
     */
    private String envId;
}

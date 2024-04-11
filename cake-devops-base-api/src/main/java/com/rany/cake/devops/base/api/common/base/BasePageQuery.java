package com.rany.cake.devops.base.api.common.base;

import lombok.Getter;

/**
 * BasePageQuery
 *
 * @author zhongshengwang
 * @description BasePageQuery
 * @date 2022/12/31 16:43
 * @email 18668485565163.com
 */

@Getter
public class BasePageQuery extends BaseQuery {

    private Integer pageNo = 0;

    private Integer pageSize = 10;

    private Boolean needTotal = true;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setNeedTotal(Boolean needTotal) {
        this.needTotal = needTotal;
    }
}

package com.rany.cake.devops.base.api.common.base;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2022/12/31 16:43
 * @email 18668485565163.com
 */

public class BasePageQuery extends BaseQuery {

    private Integer pageNo = 0;

    private Integer pageSize = 10;

    private Boolean needTotal = true;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getNeedTotal() {
        return needTotal;
    }

    public void setNeedTotal(Boolean needTotal) {
        this.needTotal = needTotal;
    }
}

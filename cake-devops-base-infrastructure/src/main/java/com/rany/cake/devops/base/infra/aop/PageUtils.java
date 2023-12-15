package com.rany.cake.devops.base.infra.aop;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;

import java.util.Collection;
import java.util.Collections;

public final class PageUtils {
    public static <T> Page<T> build(PageInfo<?> pageInfo, Collection<T> data) {
        Page<T> page = new Page<>();
        page.setPageNo(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalPage(pageInfo.getPages());
        page.setTotal(Long.valueOf(pageInfo.getTotal()).intValue());
        page.setItems(data);
        return page;
    }

    public static <T> Page<T> build(Page<?> pageInfo, Collection<T> data) {
        Page<T> page = new Page<>();
        page.setPageNo(pageInfo.getPageNo());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalPage(pageInfo.getTotalPage());
        page.setTotal(pageInfo.getTotal());
        page.setItems(data);
        return page;
    }

    public static <T> Page<T> empty(int pageNo, int pageSize) {
        Page<T> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalPage(0);
        page.setTotal(0);
        page.setItems(Collections.emptyList());
        return page;
    }
}

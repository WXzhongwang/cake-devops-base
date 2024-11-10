package com.rany.cake.devops.base.service.code;

import java.util.List;

/**
 * 代码托管服务
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:57
 * @email 18668485565163.com
 */
public abstract class BaseCodeService {

    public abstract Boolean createBranch(String repo, String branchName, String ref);

    public abstract List<Branch> listBranch(String repo, String search, Integer pageNo, Integer pageSize);
}

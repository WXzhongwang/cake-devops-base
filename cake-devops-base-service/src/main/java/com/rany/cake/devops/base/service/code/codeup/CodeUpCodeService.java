package com.rany.cake.devops.base.service.code.codeup;

import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.Branch;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CodeUpCodeService extends BaseCodeService {

    private final CodeUpService codeUpService;

    public CodeUpCodeService(String domain, String organizationId, String personalAccessToken) {
        this.codeUpService = new CodeUpService(domain, organizationId, personalAccessToken);
    }

    @Override
    public Boolean createBranch(String repo, String branchName, String ref) {
        return codeUpService.createBranch(repo, branchName, ref);
    }

    @Override
    public List<Branch> listBranch(String repo, String search, Integer pageNo, Integer pageSize) {
        List<Branch> branches = new ArrayList<>();
        List<CodeUpService.Branch> searchBranches = codeUpService.listBranches(repo, pageNo, pageSize, "updated_at", search);
        for (CodeUpService.Branch branch : searchBranches) {
            log.info("branch: {}", branch);
            Branch updatedAt = new Branch();
            updatedAt.setName(branch.getName());
            updatedAt.setDefaultBranch(branch.isDefaultBranch());
            updatedAt.setProtectedBranch(branch.isProtectedBranch());
            branches.add(updatedAt);
        }
        return branches;
    }
}

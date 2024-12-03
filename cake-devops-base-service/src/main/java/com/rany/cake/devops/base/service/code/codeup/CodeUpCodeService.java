package com.rany.cake.devops.base.service.code.codeup;

import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.Branch;
import com.rany.cake.devops.base.service.code.RepoUrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
public class CodeUpCodeService extends BaseCodeService {

    private final CodeUpService codeUpService;

    public CodeUpCodeService(String domain, String organizationId, String personalAccessToken,
                             String accessKeyId, String accessKeySecret) {
        this.codeUpService = new CodeUpService(domain, organizationId, personalAccessToken, accessKeyId, accessKeySecret);
    }

    @Override
    public Boolean createBranch(String repo, String branchName, String ref) {
        String[] repoParams = RepoUrlUtils.extractFromCodeUpSsh(repo);
        StringJoiner stringJoiner = new StringJoiner("/", "", "");
        for (String repoParam : repoParams) {
            stringJoiner.add(repoParam);
        }
        String repoEncodeURL = URL.encode(stringJoiner.toString());
        return codeUpService.createBranch(repoEncodeURL, branchName, ref);
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

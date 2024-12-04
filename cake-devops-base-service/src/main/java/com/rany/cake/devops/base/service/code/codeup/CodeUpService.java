package com.rany.cake.devops.base.service.code.codeup;

import com.aliyun.devops20210625.models.*;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CodeUpService {

    private final String organizationId;
    private final String personalAccessToken;
    private final com.aliyun.devops20210625.Client devopsClient;

    @SneakyThrows
    public CodeUpService(String organizationId, String personalAccessToken,
                         String accessKeyId, String accessKeySecret) {
        this.organizationId = organizationId;
        this.personalAccessToken = personalAccessToken;

        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(accessKeyId)
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(accessKeySecret);
        // Endpoint 请参考 https://api.aliyun.com/product/devops
        config.endpoint = "devops.cn-hangzhou.aliyuncs.com";
        this.devopsClient = new com.aliyun.devops20210625.Client(config);
    }

    public Boolean createBranch(String repo, String branch, String ref) {
        com.aliyun.devops20210625.models.CreateBranchRequest createBranchRequest = new com.aliyun.devops20210625.models.CreateBranchRequest()
                .setBranchName(branch)
                .setRef(ref)
                .setAccessToken(personalAccessToken)
                .setOrganizationId(organizationId);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        java.util.Map<String, String> headers = new java.util.HashMap<>();
        try {
            // 复制代码运行请自行打印 API 的返回值
            devopsClient.createBranchWithOptions(repo, createBranchRequest, headers, runtime);
        } catch (Exception error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            log.error(error.getMessage(), error);
            return false;
        }
        return true;
    }

    public List<Branch> listBranches(String repo, Integer page, Integer perPage, String sort, String search) {
        ListRepositoryBranchesRequest request = new ListRepositoryBranchesRequest()
                .setOrganizationId(organizationId)
                .setAccessToken(personalAccessToken)
                .setPage((long) page)
                .setPageSize((long) perPage)
                .setSearch(search)
                .setSort(sort);
        try {
            ListRepositoryBranchesResponse response = devopsClient.listRepositoryBranches(repo, request);
            ListRepositoryBranchesResponseBody body = response.body;
            List<Branch> branches = new ArrayList<>();
            List<ListRepositoryBranchesResponseBody.ListRepositoryBranchesResponseBodyResult> result = body.result;
            for (ListRepositoryBranchesResponseBody.ListRepositoryBranchesResponseBodyResult item : result) {
                log.info("Branch: {}", item.name);
                Branch element = new Branch();
                element.setName(item.name);
                element.setDefaultBranch(BooleanUtils.toBooleanObject(item._protected));
                branches.add(element);
            }
            return branches;
        } catch (Exception error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            log.error(error.getMessage(), error);
        }
        return new ArrayList<>();
    }


    public Long getRepositoryId(String repo) {
        try {
            GetRepositoryRequest request = new GetRepositoryRequest()
                    .setAccessToken(personalAccessToken)
                    .setIdentity(repo)
                    .setOrganizationId(organizationId);
            GetRepositoryResponse repository = devopsClient.getRepository(request);
            GetRepositoryResponseBody body = repository.body;
            GetRepositoryResponseBody.GetRepositoryResponseBodyRepository repositoryInfo = body.getRepository();
            return repositoryInfo.getId();
        } catch (Exception error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            log.error(error.getMessage(), error);
        }
        return null;
    }

    @Data
    public static class Branch {
        private Commit commit;
        private boolean defaultBranch;
        private String name;
        private boolean protectedBranch;
        private String webUrl;

        // Commit 类用于封装提交信息
        @Data
        public static class Commit {
            private String authorEmail;
            private String authorName;
            private String authoredDate;
            private String committedDate;
            private String committerEmail;
            private String committerName;
            private String id;
            private String message;
            private List<String> parentIds;
            private String shortId;
            private Stats stats;
            private String title;
            private String webUrl;

            @Data
            public static class Stats {
                private int additions;
                private int deletions;
                private int total;
            }
        }
    }
}

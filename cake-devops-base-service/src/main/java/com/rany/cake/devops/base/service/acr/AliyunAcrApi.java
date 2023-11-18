package com.rany.cake.devops.base.service.acr;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.cr20181201.AsyncClient;
import com.aliyun.sdk.service.cr20181201.models.CreateRepositoryRequest;
import com.aliyun.sdk.service.cr20181201.models.CreateRepositoryResponse;
import com.rany.cake.devops.base.domain.base.CrConfig;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * AliyunAcrApi
 *
 * @author zhongshengwang
 * @description AliyunAcrApi
 * @date 2023/1/20 20:40
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class AliyunAcrApi {

    @Resource
    private CrConfig crConfig;


    public void createRepo(String repoName) {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(crConfig.getAliyunConf().getAccessKey())
                .accessKeySecret(crConfig.getAliyunConf().getSecretKey())
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .credentialsProvider(provider)
                .overrideConfiguration(ClientOverrideConfiguration.create()
                        .setEndpointOverride("cr.cn-hangzhou.aliyuncs.com"))
                .build();


        CreateRepositoryRequest request = CreateRepositoryRequest.builder()
                .repoName(repoName)
                .repoNamespaceName(crConfig.getAliyunConf().getNamespace())
                .regionId(crConfig.getAliyunConf().getRegion())
                .repoType("PRIVATE").build();

        try {
            CompletableFuture<CreateRepositoryResponse> response = client.createRepository(request);
            CreateRepositoryResponse createRepositoryResponse = response.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        // Finally, close the client
        client.close();
    }
}

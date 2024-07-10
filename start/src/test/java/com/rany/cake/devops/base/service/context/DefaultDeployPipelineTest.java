package com.rany.cake.devops.base.service.context;


import com.rany.cake.devops.base.CakeDevopsBaseApplication;
import com.rany.cake.devops.base.domain.aggregate.*;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.ReleaseCenter;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.toolkit.lang.constant.Letters;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.ShellExecutor;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 19:21
 * @email 18668485565163.com
 */
@SpringBootTest(classes = CakeDevopsBaseApplication.class)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(properties = {"websocket.enabled=false"})
public class DefaultDeployPipelineTest {

    private static final Logger log = LoggerFactory.getLogger(DefaultDeployPipelineTest.class);
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private ClusterRepository clusterRepository;
    @Autowired
    private NameSpaceRepository nameSpaceRepository;
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ReleaseCenter releaseCenter;
    @Autowired
    private HostDomainService hostDomainService;
    @Autowired
    private HostConnectionService hostConnectionService;


    @Test
    public void test01_testApp() {
        App app = appRepository.find(new AppId("781513981771788288"));
        Assertions.assertNotNull(app);
    }

    @Test
    public void start() {
        Release release = releaseRepository.find(new ReleaseId("906359016366682112"));
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));
        releaseCenter.release(release, app, appEnv, namespace, cluster);
    }

    @Test
    public void test() throws InterruptedException {
        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        // 格式化日期和时间
        String formattedDateTime = now.format(formatter);
        String workspace = String.format(Constants.REMOTE_BASE + "/" + "cake-devops-base" + "/" + formattedDateTime);
        log.info("workspace directory: " + workspace);

        Host deployHost = hostDomainService.getHost(new HostId("936499355525984256"));
        try (SessionStore sessionStore = hostConnectionService.openSessionStore(deployHost)) {
            ShellExecutor executor = sessionStore.getShellExecutor();
            executor.connect();
            executor.scheduler(SchedulerPools.TERMINAL_SCHEDULER);
            executor.streamHandler(inputStream -> {
                byte[] bs = new byte[Const.BUFFER_KB_4];
                BufferedInputStream in = new BufferedInputStream(inputStream, Const.BUFFER_KB_4);
                int read;
                try {
                    while ((read = in.read(bs)) != -1) {
                        // 响应
                        try (ByteArrayOutputStream o = new ByteArrayOutputStream()) {
                            o.write(bs, 0, read);
                            log.info(o.toString());
                        }
                    }
                } catch (IOException ex) {
                    log.error("terminal 读取流失败", ex);
                }
            });


            executor.write(Strings.bytes("ls -la"));
            executor.write(new byte[]{Letters.LF});
        }
        new CountDownLatch(1).await();
    }
}
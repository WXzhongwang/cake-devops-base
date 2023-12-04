package com.rany.cake.devops.base;


import com.rany.cake.devops.base.lock.LockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LockTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LockTest {
    @Resource
    private LockService lockService;

    /**
     * 测试分布式锁
     */
    @Test
    public void test1() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        IntStream.range(0, 30).forEach(i -> executorService.submit(() -> {
            try {
                lockService.seckill("20120508784");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }

    /**
     * 测试分布式锁
     */
    @Test
    public void test2() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        IntStream.range(0, 30).forEach(i -> executorService.submit(() -> {
            try {
                lockService.seckill2("20120508784");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }

    /**
     * 测试分布式锁(模拟重复提交)
     */
    @Test
    public void test3() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        IntStream.range(0, 20).forEach(i -> executorService.submit(() -> {
            try {
                lockService.reSubmit("test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }
}

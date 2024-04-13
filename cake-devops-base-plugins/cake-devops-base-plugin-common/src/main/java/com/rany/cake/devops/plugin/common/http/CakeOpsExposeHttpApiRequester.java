package com.rany.cake.devops.plugin.common.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * orion-ops expose-api 请求器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/9/1 10:31
 */
@Component
public class CakeOpsExposeHttpApiRequester implements HttpApiRequester<CakeOpsExposeHttpApi> {

    private static final String TAG = "cake-ops-api";

    private static String CAKE_OPS_ACCESS_HOST;

    private static String CAKE_OPS_ACCESS_HEADER;

    private static String CAKE_OPS_ACCESS_SECRET;

    private CakeOpsExposeHttpApi api;

    private CakeOpsExposeHttpApiRequester() {
    }

    public CakeOpsExposeHttpApiRequester(CakeOpsExposeHttpApi api) {
        this.api = api;
    }

    /**
     * 获取请求
     *
     * @param api api
     * @return request
     */
    public static HttpApiRequest create(CakeOpsExposeHttpApi api) {
        return new CakeOpsExposeHttpApiRequester(api).getRequest();
    }

    @Override
    public HttpApiRequest getRequest() {
        HttpApiRequest request = new HttpApiRequest(CAKE_OPS_ACCESS_HOST, api);
        request.tag(TAG);
        request.header(CAKE_OPS_ACCESS_HEADER, CAKE_OPS_ACCESS_SECRET);
        return request;
    }

    @Value("${cake.ops.access.host:}")
    private void setOrionOpsAccessHost(String orionOpsAccessHost) {
        CAKE_OPS_ACCESS_HOST = orionOpsAccessHost;
    }

    @Value("${cake.ops.access.header:}")
    private void setOrionOpsAccessHeader(String orionOpsAccessHeader) {
        CAKE_OPS_ACCESS_HEADER = orionOpsAccessHeader;
    }

    @Value("${cake.ops.access.secret:}")
    private void setOrionOpsAccessSecret(String orionOpsAccessSecret) {
        CAKE_OPS_ACCESS_SECRET = orionOpsAccessSecret;
    }

}

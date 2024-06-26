package com.rany.cake.devops.plugin.common.http;

import com.alibaba.fastjson.TypeReference;
import com.rany.cake.toolkit.http.ok.OkResponse;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;

/**
 * http api 请求器基类
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/1 10:55
 */
public interface HttpApiRequester<API extends HttpApiDefined> {

    /**
     * 请求 api
     *
     * @return OkResponse
     */
    default OkResponse await() {
        return this.getRequest().await();
    }

    /**
     * 请求 api
     *
     * @param dataClass dataClass
     * @param <T>       T
     * @return HttpWrapper
     */
    default <T> HttpWrapper<T> request(Class<T> dataClass) {
        return this.getRequest().getHttpWrapper(dataClass);
    }

    /**
     * 请求 api
     *
     * @param type type
     * @param <T>  T
     * @return T
     */
    default <T> T request(TypeReference<T> type) {
        return this.getRequest().getJson(type);
    }

    /**
     * 请求 api
     *
     * @param requestBody requestBody
     * @param dataClass   dataClass
     * @param <T>         T
     * @return HttpWrapper
     */
    default <T> HttpWrapper<T> request(Object requestBody, Class<T> dataClass) {
        return this.getRequest().jsonBody(requestBody).getHttpWrapper(dataClass);
    }

    /**
     * 请求 api
     *
     * @param requestBody requestBody
     * @param type        type
     * @param <T>         T
     * @return T
     */
    default <T> T request(Object requestBody, TypeReference<T> type) {
        return this.getRequest().jsonBody(requestBody).getJson(type);
    }

    /**
     * 获取 api request
     *
     * @return request
     */
    HttpApiRequest getRequest();

}

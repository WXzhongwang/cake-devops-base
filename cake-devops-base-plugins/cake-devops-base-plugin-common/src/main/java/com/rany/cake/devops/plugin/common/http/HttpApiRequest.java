package com.rany.cake.devops.plugin.common.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rany.cake.toolkit.http.ok.OkRequest;
import com.rany.cake.toolkit.lang.constant.StandardContentType;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;

/**
 * http api 请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/1 10:57
 */
public class HttpApiRequest extends OkRequest {

    public HttpApiRequest(String url, HttpApiDefined api) {
        this.url = url + api.getPath();
        this.method = api.getMethod().method();
    }

    /**
     * 设置 json body
     *
     * @param body body
     * @return this
     */
    public HttpApiRequest jsonBody(Object body) {
        this.contentType = StandardContentType.APPLICATION_JSON;
        this.body(JSON.toJSONString(body));
        return this;
    }

    /**
     * 请求获取 httpWrapper
     *
     * @param dataClass T
     * @param <T>       T
     * @return httpWrapper
     */
    public <T> HttpWrapper<T> getHttpWrapper(Class<T> dataClass) {
        return this.await().getJsonObjectBody(new TypeReference<HttpWrapper<T>>(dataClass) {
        });
    }

    /**
     * 请求获取 json
     *
     * @param type type
     * @param <T>  T
     * @return T
     */
    public <T> T getJson(TypeReference<T> type) {
        return this.await().getJsonObjectBody(type);
    }

}

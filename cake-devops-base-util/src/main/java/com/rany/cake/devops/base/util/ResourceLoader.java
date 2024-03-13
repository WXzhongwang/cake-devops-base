package com.rany.cake.devops.base.util;

import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * 资源加载器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/16 17:58
 */
@Slf4j
public class ResourceLoader {

    /**
     * key: resource
     * value: content
     */
    private static final Map<String, String> RESOURCE = Maps.newMap();

    /**
     * 获取资源
     *
     * @param resource resource
     * @param loader   加载类
     * @return content
     */
    public static String get(String resource, Class<?> loader) {
        try (InputStream stream = loader.getResourceAsStream(resource)) {
            String content = Streams.toString(Objects.requireNonNull(stream));
            RESOURCE.put(resource, content);
            return content;
        } catch (IOException e) {
            throw Exceptions.notFound(Strings.format("resource not found {} by {}", resource, loader.getName()), e);
        }
    }

}

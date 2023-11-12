package com.rany.cake.devops.base.infra.convertor;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 基础转化器
 *
 * @author zhongshengwang
 * @description 基础转化器
 * @date 2022/12/28 21:07
 * @email 18668485565163.com
 */
@MapperConfig
public interface BaseConvertor<SOURCE, TARGET> {

    /**
     * 映射同名属性
     *
     * @param source
     * @return
     */
    TARGET sourceToTarget(SOURCE source);

    /**
     * 反向，映射同名属性
     *
     * @param target
     * @return
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    SOURCE targetToSource(TARGET target);

    /**
     * 映射同名属性，集合形式
     *
     * @param sources
     * @return
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<TARGET> sourceToTarget(List<SOURCE> sources);

    /**
     * 反向，映射同名属性，集合形式
     *
     * @param targets
     * @return
     */
    @InheritConfiguration(name = "targetToSource")
    List<SOURCE> targetToSource(List<TARGET> targets);

    /**
     * 映射同名属性，集合流形式
     *
     * @param stream
     * @return
     */
    List<TARGET> sourceToTarget(Stream<SOURCE> stream);

    /**
     * 反向，映射同名属性，集合流形式
     *
     * @param stream
     * @return
     */
    List<SOURCE> targetToSource(Stream<TARGET> stream);

    default String convertString(List<String> items) {
        if (items != null && !items.isEmpty()) {
            return StringUtils.join(items, ",");
        }
        return null;
    }

    default List<String> convertList(String value) {
        if (StringUtils.isNotBlank(value)) {
            String[] split = value.split(",");
            return Arrays.stream(split).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
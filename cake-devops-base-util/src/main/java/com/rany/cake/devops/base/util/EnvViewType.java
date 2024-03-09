package com.rany.cake.devops.base.util;

import com.rany.cake.toolkit.lang.collect.MutableLinkedHashMap;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * 环境变量查看类型
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/8/3 18:53
 */
@AllArgsConstructor
public enum EnvViewType {

    /**
     * json
     */
    JSON(10) {
        @Override
        public String toValue(Map<String, String> value) {
            return AttrConverts.toJson(value);
        }

        @Override
        public MutableLinkedHashMap<String, String> toMap(String value) {
            return AttrConverts.fromJson(value);
        }
    },

    /**
     * xml
     */
    XML(20) {
        @Override
        public String toValue(Map<String, String> value) {
            return AttrConverts.toXml(value);
        }

        @Override
        public MutableLinkedHashMap<String, String> toMap(String value) {
            return AttrConverts.fromXml(value);
        }
    },

    /**
     * yml
     */
    YML(30) {
        @Override
        public String toValue(Map<String, String> value) {
            return AttrConverts.toYml(value);
        }

        @Override
        public MutableLinkedHashMap<String, String> toMap(String value) {
            return AttrConverts.fromYml(value);
        }
    },

    /**
     * properties
     */
    PROPERTIES(40) {
        @Override
        public String toValue(Map<String, String> value) {
            return AttrConverts.toProperties(value);
        }

        @Override
        public MutableLinkedHashMap<String, String> toMap(String value) {
            return AttrConverts.fromProperties(value);
        }
    },

    ;

    private final Integer type;

    public abstract String toValue(Map<String, String> value);

    public abstract MutableLinkedHashMap<String, String> toMap(String value);

    public static EnvViewType of(Integer type) {
        if (type == null) {
            return null;
        }
        for (EnvViewType value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}

package com.rany.cake.devops.base.service.plugins;

import com.rany.cake.devops.base.service.context.Plugin;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 基础插件
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/1 19:48
 * @email 18668485565163.com
 */
@Slf4j
public abstract class BasePlugin implements Plugin {

    protected Map<String, Object> fieldMap = new HashMap<>();

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public boolean has(String key) {
        if (key == null) {
            log.warn("key is null");
            return false;
        }
        return fieldMap.containsKey(key);
    }

    public void put(String key, Object value) {
        if (key == null) {
            log.warn("key is null");
            return;
        }
        if (value == null) {
            log.warn("value is null");
            return;
        }
        if (fieldMap.containsKey(key)) {
            log.debug("key[{}] has existed, overwrite it", key);
        }
        fieldMap.put(key, value);
    }

    public boolean isEmpty() {
        return fieldMap == null || fieldMap.isEmpty();
    }

    public int getFieldCnt() {
        return fieldMap == null ? 0 : fieldMap.size();
    }

    public Object get(String key) {
        if (key == null) {
            log.warn("key is null");
            return null;
        }
        return fieldMap.getOrDefault(key, null);
    }

    public boolean remove(String key) {
        if (fieldMap.containsKey(key)) {
            fieldMap.remove(key);
            return true;
        }
        return false;
    }

    public void clear() {
        fieldMap.clear();
    }

    public Iterator<Map.Entry<String, Object>> iterator() {
        return fieldMap.entrySet().iterator();
    }

    public List<String> keys() {
        return new ArrayList<>(fieldMap.keySet());
    }
}

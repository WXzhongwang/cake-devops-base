package com.rany.cake.devops.base.infra.handler;

import com.alibaba.fastjson.JSON;
import com.rany.cake.toolkit.lang.utils.Maps;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author zhongshengwang
 */
@MappedJdbcTypes({JdbcType.VARCHAR})
@MappedTypes({Map.class})
public class MapPairTypeHandler implements TypeHandler<Map<String, Object>> {
    public MapPairTypeHandler() {
    }

    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从ResultSet中获取JSON字符串并使用fastjson转换为Map
        String jsonStr = rs.getString(columnIndex);
        if (jsonStr == null || jsonStr.isEmpty()) {
            return Maps.newMap();
        }
        return JSON.parseObject(jsonStr, Map.class);
    }

    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从CallableStatement中获取JSON字符串并使用fastjson转换为Map
        String jsonStr = cs.getString(columnIndex);
        if (jsonStr == null || jsonStr.isEmpty()) {
            return Maps.newMap();
        }
        return JSON.parseObject(jsonStr, Map.class);
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int columnIndex, Map<String, Object> parameter, JdbcType jdbcType) throws SQLException {
        // 使用fastjson将Map转换为JSON字符串
        if (parameter == null || parameter.isEmpty()) {
            preparedStatement.setString(columnIndex, null);
            return;
        }
        String jsonStr = JSON.toJSONString(parameter);
        preparedStatement.setString(columnIndex, jsonStr);
    }

    @Override
    public Map<String, Object> getResult(ResultSet resultSet, String columnName) throws SQLException {
        // 从ResultSet中获取JSON字符串并使用fastjson转换为Map
        String jsonStr = resultSet.getString(columnName);
        return JSON.parseObject(jsonStr, Map.class);
    }

    @Override
    public Map<String, Object> getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        // 从ResultSet中获取JSON字符串并使用fastjson转换为Map
        String jsonStr = resultSet.getString(columnIndex);
        if (jsonStr == null || jsonStr.isEmpty()) {
            return Maps.newMap();
        }
        return JSON.parseObject(jsonStr, Map.class);
    }

    @Override
    public Map<String, Object> getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        // 从ResultSet中获取JSON字符串并使用fastjson转换为Map
        String jsonStr = callableStatement.getString(columnIndex);
        if (jsonStr == null || jsonStr.isEmpty()) {
            return Maps.newMap();
        }
        return JSON.parseObject(jsonStr, Map.class);
    }
}

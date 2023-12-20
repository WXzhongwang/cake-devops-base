package com.rany.cake.devops.base.domain.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "devops.biz-config")
public class DepartmentConfig {

    private List<Department> departments;

    @Data
    public static class Department {
        private String label;
        private String value;
        private String abbr;
    }
}

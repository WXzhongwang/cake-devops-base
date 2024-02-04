package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.ScriptTemplate;
import com.rany.cake.devops.base.domain.repository.param.ScriptTemplateQueryParam;

public interface ScriptTemplateRepository {
    ScriptTemplate find(Long id);

    void remove(ScriptTemplate scriptTemplate);

    void save(ScriptTemplate scriptTemplate);

    void update(ScriptTemplate scriptTemplate);

    Page<ScriptTemplate> page(ScriptTemplateQueryParam queryParam);
}

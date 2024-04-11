package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.devops.base.api.dto.ScriptTemplateDTO;
import com.rany.cake.devops.base.api.query.template.ScriptTemplatePageQuery;
import com.rany.cake.devops.base.domain.entity.ScriptTemplate;
import com.rany.cake.devops.base.domain.repository.param.ScriptTemplateQueryParam;
import com.rany.cake.devops.base.infra.convertor.BaseConvertor;
import org.mapstruct.Mapper;

/**
 * webhook
 *
 * @author zhongshengwang
 * @description webhook
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ScriptTemplateDataAdapter extends BaseConvertor<ScriptTemplate, ScriptTemplateDTO> {

    ScriptTemplateQueryParam convertParam(ScriptTemplatePageQuery scriptTemplatePageQuery);
}

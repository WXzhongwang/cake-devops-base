package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.ScriptTemplate;
import com.rany.cake.devops.base.infra.po.ScriptTemplatePO;
import org.mapstruct.Mapper;

/**
 * 脚本
 *
 * @author zhongshengwang
 * @description 脚本
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface ScriptTemplateDataConvertor extends BaseConvertor<ScriptTemplate, ScriptTemplatePO> {


}

package com.rany.cake.devops.base.infra.convertor;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.infra.po.FileTransferLogPO;
import org.mapstruct.Mapper;

/**
 * 传输日志
 *
 * @author zhongshengwang
 * @description 审批转换器
 * @date 2022/11/26 00:27
 * @email 18668485565163.com
 */
@Mapper(componentModel = "spring")
public interface FileTransferLogDataConvertor extends BaseConvertor<FileTransferLog, FileTransferLogPO> {
    
}

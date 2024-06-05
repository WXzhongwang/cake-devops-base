package com.rany.cake.devops.base.service.adapter.conversions;


import com.rany.cake.devops.base.api.dto.FileTransferLogDTO;
import com.rany.cake.devops.base.infra.po.FileTransferLogPO;
import com.rany.cake.toolkit.lang.convert.TypeStore;
import com.rany.cake.toolkit.lang.io.Files1;

/**
 * 文件传输日志 对象转换器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/10 18:07
 */
public class FileTransferLogConversion {

    static {
        TypeStore.STORE.register(FileTransferLogPO.class, FileTransferLogDTO.class, p -> {
            FileTransferLogDTO vo = new FileTransferLogDTO();
            vo.setId(p.getId());
            vo.setHostId(p.getHostId());
            vo.setFileToken(p.getFileToken());
            vo.setType(p.getTransferType().intValue());
            vo.setRemoteFile(p.getRemoteFile());
            vo.setCurrent(Files1.getSize(p.getCurrentSize()));
            vo.setSize(Files1.getSize(p.getFileSize()));
            vo.setProgress(p.getNowProgress());
            vo.setStatus(p.getTransferStatus().intValue());
            return vo;
        });
    }

}

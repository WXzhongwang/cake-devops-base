package com.rany.cake.devops.base.service.adapter.conversions;


import com.rany.cake.devops.base.api.dto.system.SystemAnalysisDTO;
import com.rany.cake.devops.base.api.dto.system.SystemSpaceAnalysisDTO;
import com.rany.cake.toolkit.lang.convert.TypeStore;

/**
 * 系统统计分析 对象转换器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/10 18:22
 */
public class SystemAnalysisConversion {

    static {
        TypeStore.STORE.register(SystemSpaceAnalysisDTO.class, SystemAnalysisDTO.class, p -> {
            SystemAnalysisDTO vo = new SystemAnalysisDTO();
            vo.setTempFileCount(p.getTempFileCount());
            vo.setTempFileSize(p.getTempFileSize());
            vo.setLogFileCount(p.getLogFileCount());
            vo.setLogFileSize(p.getLogFileSize());
            vo.setSwapFileCount(p.getSwapFileCount());
            vo.setSwapFileSize(p.getSwapFileSize());
            vo.setDistVersionCount(p.getDistVersionCount());
            vo.setDistFileSize(p.getDistFileSize());
            vo.setRepoVersionCount(p.getRepoVersionCount());
            vo.setRepoVersionFileSize(p.getRepoVersionFileSize());
            vo.setScreenFileCount(p.getScreenFileCount());
            vo.setScreenFileSize(p.getScreenFileSize());
            return vo;
        });
    }

}

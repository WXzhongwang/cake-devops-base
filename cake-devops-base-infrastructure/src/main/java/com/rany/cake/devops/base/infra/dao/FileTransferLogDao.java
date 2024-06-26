package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.domain.repository.param.FileTransferLogParam;
import com.rany.cake.devops.base.infra.po.FileTransferLogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileTransferLogDao {


    /**
     * 新增
     *
     * @param log 日志
     * @return
     */
    int save(FileTransferLog log);


    /**
     * 根据id查询传输日志
     *
     * @param id id
     * @return
     */
    FileTransferLogPO selectById(Long id);

    /**
     * token查日志
     *
     * @param token token
     * @return
     */
    FileTransferLogPO selectByToken(@Param("token") String token,
                                    @Param("userId") String userId);

    List<FileTransferLogPO> selectTransferLogByParam(FileTransferLogParam param);


    /**
     * 更新
     *
     * @param log 日志
     * @return
     */
    int update(FileTransferLog log);


    int transferClear(@Param("userId") String userId, @Param("status") Byte status);
}

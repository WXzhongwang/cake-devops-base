package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.domain.repository.param.FileTransferLogParam;

import java.util.List;

public interface FileTransferLogRepository {
    FileTransferLog find(Long id);

    void save(FileTransferLog log);

    int update(FileTransferLog log);

    FileTransferLog getTransferLogByToken(String fileToken, String userId);

    List<FileTransferLog> getTransferLogByParam(FileTransferLogParam param);

    int transferClear(String userId, Byte status);
}

package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;

public interface FileTransferLogRepository {
    FileTransferLog find(Long id);

    void save(FileTransferLog log);

    int update(FileTransferLog log);
}

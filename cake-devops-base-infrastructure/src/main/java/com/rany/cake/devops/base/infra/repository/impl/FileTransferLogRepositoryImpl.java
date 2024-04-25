package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.domain.repository.FileTransferLogRepository;
import com.rany.cake.devops.base.domain.repository.param.FileTransferLogParam;
import com.rany.cake.devops.base.infra.convertor.FileTransferLogDataConvertor;
import com.rany.cake.devops.base.infra.dao.FileTransferLogDao;
import com.rany.cake.devops.base.infra.mapper.FileTransferLogPOMapper;
import com.rany.cake.devops.base.infra.po.FileTransferLogPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class FileTransferLogRepositoryImpl implements FileTransferLogRepository {

    private final FileTransferLogPOMapper fileTransferLogPOMapper;
    private final FileTransferLogDao fileTransferLogDao;
    private final FileTransferLogDataConvertor fileTransferLogDataConvertor;

    @Override
    public FileTransferLog find(Long id) {
        FileTransferLogPO fileTransferLogPO = fileTransferLogDao.selectById(id);
        return fileTransferLogDataConvertor.targetToSource(fileTransferLogPO);
    }

    @Override
    public void save(FileTransferLog log) {
        fileTransferLogDao.save(log);
    }

    @Override
    public int update(FileTransferLog log) {
        return fileTransferLogDao.update(log);
    }

    @Override
    public FileTransferLog getTransferLogByToken(String fileToken, String userId) {
        FileTransferLogPO fileTransferLogPO = fileTransferLogDao.selectByToken(fileToken, userId);
        return fileTransferLogDataConvertor.targetToSource(fileTransferLogPO);
    }

    @Override
    public List<FileTransferLog> getTransferLogByParam(FileTransferLogParam param) {
        List<FileTransferLogPO> fileTransferLogPOList = fileTransferLogDao.selectTransferLogByParam(param);
        return fileTransferLogDataConvertor.targetToSource(fileTransferLogPOList);
    }

    @Override
    public int transferClear(String userId, Byte status) {
        return fileTransferLogDao.transferClear(userId, status);
    }
}

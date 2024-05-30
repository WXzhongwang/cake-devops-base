package com.rany.cake.devops.base.service.adapter.conversions;

import com.rany.cake.devops.base.api.dto.FileDetailDTO;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.toolkit.lang.convert.TypeStore;
import com.rany.cake.toolkit.lang.io.FileType;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.net.base.file.sftp.SftpFile;

import java.util.Optional;

/**
 * sftp 对象转换器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2022/8/10 16:51
 */
public class SftpConversion {

    static {
        TypeStore.STORE.register(SftpFile.class, FileDetailDTO.class, s -> {
            FileDetailDTO vo = new FileDetailDTO();
            vo.setName(s.getName());
            vo.setPath(s.getPath());
            vo.setSize(Files1.getSize(s.getSize()));
            vo.setSizeByte(s.getSize());
            vo.setPermission(s.getPermission());
            vo.setUid(s.getUid());
            vo.setGid(s.getGid());
            vo.setAttr(s.getPermissionString());
            vo.setModifyTime(s.getModifyTime());
            Boolean isDir = Optional.ofNullable(FileType.of(vo.getAttr()))
                    .map(FileType.DIRECTORY::equals)
                    .orElse(false);
            vo.setIsDir(isDir);
            vo.setIsSafe(!Const.UNSAFE_FS_DIR.contains(s.getPath()));
            return vo;
        });
    }

//    static {
//        TypeStore.STORE.register(FileUploadRequest.class, SftpUploadInfoDTO.class, p -> {
//            SftpUploadInfoDTO dto = new SftpUploadInfoDTO();
//            dto.setRemotePath(p.getRemotePath());
//            return dto;
//        });
//    }

}

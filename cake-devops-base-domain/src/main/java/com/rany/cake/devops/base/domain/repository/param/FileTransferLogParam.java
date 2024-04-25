package com.rany.cake.devops.base.domain.repository.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileTransferLogParam extends BaseParam {
    private String userId;
    private String hostId;
    private List<Byte> transferStatus;
    private List<Byte> transferType;
}

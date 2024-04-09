package com.rany.cake.devops.base.api.command.member;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateAppMemberCommand extends BaseCommand {
    private String memberId;
    private List<String> roles;
    private String status;
}

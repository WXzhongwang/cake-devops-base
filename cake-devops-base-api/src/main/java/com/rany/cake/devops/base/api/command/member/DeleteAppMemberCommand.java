package com.rany.cake.devops.base.api.command.member;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeleteAppMemberCommand extends BaseCommand {
    private String memberId;
}

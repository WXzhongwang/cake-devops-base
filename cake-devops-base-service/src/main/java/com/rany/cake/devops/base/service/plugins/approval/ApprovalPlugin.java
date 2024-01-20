package com.rany.cake.devops.base.service.plugins.approval;

import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.enums.ApprovalStatus;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.repository.ApprovalRepository;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发布审批插件
 *
 * @author zhongshengwang
 * @description 发布审批插件
 * @date 2023/1/20 19:41
 * @email 18668485565163.com
 */
@Component
@PluginName("发布审批校验")
public class ApprovalPlugin extends BasePlugin {

    @Resource
    private ApprovalRepository approvalRepository;

    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @SneakyThrows
    @Override
    public boolean execute(DeployContext context) {
        ApprovalId approvalId = context.getRelease().getApprovalId();
        while (true) {
            Approval approval = approvalRepository.find(approvalId);
            if (approval == null) {
                log.warn("审批未找到:{}", approvalId.getApprovalId());
                return false;
            }
            if (StringUtils.equals(approval.getApprovalStatus(), ApprovalStatus.AUTO_APPROVED.name()) ||
                    StringUtils.equals(approval.getApprovalStatus(), ApprovalStatus.APPROVED.name())
            ) {
                break;
            }
            Thread.sleep(2000);
        }
        return true;
    }
}

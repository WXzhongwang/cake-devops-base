package com.rany.cake.devops.base.service.integration.alert;

import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.toolkit.alert.alert.DingAlertService;
import com.rany.cake.toolkit.alert.alert.impl.DingAlertServiceImpl;
import com.rany.cake.toolkit.alert.alert.model.DingActionCardAlertEntity;
import com.rany.cake.toolkit.alert.alert.model.WebHookParam;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Strings;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/9 13:41
 * @slogon 找到银弹
 */
@Component
public class ApprovalNotifier {

    @Resource
    protected DingNotifyConfig dingNotifyConfig;
    protected DingAlertService dingAlertService = new DingAlertServiceImpl();

    public void publishApproval(String publisherName, Approval approval) {
        WebHookParam webHookParam = new WebHookParam(dingNotifyConfig.getWebhook(),
                dingNotifyConfig.getSign(),
                dingNotifyConfig.getSecret());
        DingActionCardAlertEntity alertEntity = new DingActionCardAlertEntity(webHookParam);
        alertEntity.setTitle(Strings.format("%s提交的发布申请", publisherName));
        String publishDate = Dates.format(approval.getChangeDate(), "yyyy-MM-dd HH:mm:ss");
        String errorContent =
                "#### %s提交的发布申请\n" +
                        "预计发布日期: %s\n" +
                        "备注: %s\n" +
                        "###### 文档地址: [jump](%s)";
        alertEntity.setText(Strings.format(errorContent,
                publisherName,
                publishDate,
                approval.getChangeDate(),
                approval.getDocAddress()));

        dingAlertService.sendDingTalkActionCardMsg(alertEntity);
    }
}

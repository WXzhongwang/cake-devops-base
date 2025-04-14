package com.rany.cake.devops.base.notify;


import com.rany.cake.toolkit.alert.alert.DingAlertService;
import com.rany.cake.toolkit.alert.alert.enums.Level;
import com.rany.cake.toolkit.alert.alert.impl.DingAlertServiceImpl;
import com.rany.cake.toolkit.alert.alert.model.*;
import com.rany.cake.toolkit.lang.utils.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DingAlertServiceTest {

    private DingAlertService dingAlertService = new DingAlertServiceImpl();

    private final WebHookParam webHookParam = new WebHookParam("https://oapi.dingtalk.com/robot/send?access_token=", false,
            "");


    @Test
    public void sendDingTalkTextMsg() {
        DingTextAlertEntity alertEntity = new DingTextAlertEntity(webHookParam, "严重告警", Level.ERROR);
        alertEntity.setIsAtAll(true);
        alertEntity.setMobiles(Sets.of("18668485565"));
        dingAlertService.sendDingTalkTextMsg(alertEntity);
    }

    @Test
    public void sendDingTalkLinkMsg() {
        DingLinkAlertEntity alertEntity = new DingLinkAlertEntity(webHookParam,
                "测试链接消息", "国庆节快乐", "https://developers.dingtalk.com/");
        dingAlertService.sendDingTalkLinkMsg(alertEntity);
    }

    @Test
    public void sendDingTalkMarkDownMsg() {
        DingMarkDownAlertEntity markdown = new DingMarkDownAlertEntity(webHookParam, "天气预报",
                "#### 杭州天气 @18668485565\n" +
                        "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                        "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
                        "> ###### 10点20分发布 [天气](https://www.thinkpage.cn/) \n");
        dingAlertService.sendDingTalkMarkDownMsg(markdown);
    }

    @Test
    public void sendDingTalkActionCardMsg() {
        DingActionCardAlertEntity action = new DingActionCardAlertEntity(webHookParam);
        action.setText("[Cova]独立跳转卡片，请点击查看原文");
        action.setTitle("卡片标题");
        action.setHideAvatar("0");
        action.setBtnOrientation("1");
        action.setSingleTitle("独立标题");
        action.setSingleURL("https://baidu.com");
        dingAlertService.sendDingTalkActionCardMsg(action);
    }

    @Test
    public void sendDingTalkMultiActionCardMsg() {
        DingMultiActionCardAlertEntity action = new DingMultiActionCardAlertEntity(webHookParam);
        action.setText("文本");
        action.setTitle("标题");
        action.setHideAvatar("0");
        action.setBtnOrientation("1");
        List<ActionCardBtnItem> buttons = new ArrayList<>();
        ActionCardBtnItem kuaishou = new ActionCardBtnItem();
        kuaishou.setActionUrl("https://www.kuaishou.com/");
        kuaishou.setTitle("快手");
        ActionCardBtnItem douyin = new ActionCardBtnItem();
        douyin.setActionUrl("https://www.douyin.com/");
        douyin.setTitle("抖音");
        buttons.add(kuaishou);
        buttons.add(douyin);
        action.setButtons(buttons);
        dingAlertService.sendDingTalkMultiActionCardMsg(action);
    }

    @Test
    public void sendDingTalkFeedCardMsg() {
        List<FeedCardItem> feeds = new ArrayList<>();
        FeedCardItem douyin = new FeedCardItem();
        douyin.setTitle("进入抖音");
        douyin.setPicURL("https://img1.baidu.com/it/u=1155810471,3627660053&fm=26&fmt=auto");
        douyin.setMessageURL("https://www.douyin.com/");

        FeedCardItem kuaishou = new FeedCardItem();
        kuaishou.setTitle("进入快手");
        kuaishou.setPicURL("https://img1.baidu.com/it/u=2783411556,3430459830&fm=26&fmt=auto");
        kuaishou.setMessageURL("https://www.kuaishou.com/");
        feeds.add(douyin);
        feeds.add(kuaishou);

        DingFeedCardAlertEntity feed = new DingFeedCardAlertEntity(webHookParam, feeds);
        dingAlertService.sendDingTalkFeedCardMsg(feed);
    }
}
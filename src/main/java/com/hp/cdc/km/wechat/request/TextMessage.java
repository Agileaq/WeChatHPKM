package com.hp.cdc.km.wechat.request;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-3
 * Time: AM1:17
 * To change this template use File | Settings | File Templates.
 */
public class TextMessage extends BaseMessage{
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}

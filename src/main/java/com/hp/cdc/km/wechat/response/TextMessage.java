package com.hp.cdc.km.wechat.response;

import com.hp.cdc.km.wechat.util.MessageUtil;

/**
 * 文本消息
 *
 * @author liufeng
 * @date 2013-05-19
 */
public class TextMessage extends BaseMessage {

    public TextMessage() {
        setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
    }
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toXML() {
        return MessageUtil.textMessageToXml(this);
    }
}
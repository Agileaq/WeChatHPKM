package com.hp.cdc.km.wechat.response;

import java.util.Date;

/**
 * 消息基类（公众帐号 -> 普通用户）
 *
 * @author liufeng
 * @date 2013-05-19
 */
public abstract class BaseMessage {
    // 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime=new Date().getTime();
    // 消息类型（text/music/news）
    private String MsgType;




    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    protected void setMsgType(String msgType) {
        MsgType = msgType;
    }


    public abstract String toXML();

}

package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.wechat.response.Article;
import com.hp.cdc.km.wechat.response.BaseMessage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-5
 * Time: AM7:12
 * To change this template use File | Settings | File Templates.
 */
public interface ITemplateBuilder {

    public BaseMessage buildMessage(Map<String, Object> template, Map<String,Object> context);

    public Article buildArticle(Map<String,Object> template, Map<String, Object> context);
}

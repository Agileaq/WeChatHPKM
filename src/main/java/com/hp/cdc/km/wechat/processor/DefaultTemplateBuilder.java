package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.wechat.response.Article;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.response.TextMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;
import com.hp.cdc.km.wechat.util.TemplateUtil;

import java.util.Date;
import java.util.Map;



/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-5
 * Time: AM7:16
 * To change this template use File | Settings | File Templates.
 */
public class DefaultTemplateBuilder implements ITemplateBuilder {


    @Override
    /**
     * This is to build a message from message template.
     * currently, we only support the text type message
     * since we didn't setup database yet
     */
    public BaseMessage buildMessage(Map<String, Object> msgTemplate, Map<String, Object> context) {

        String fromUserName = (String) context.get(MessageUtil.FROM_USER_NAME);
        // 公众帐号
        String toUserName = (String) context.get(MessageUtil.TO_USER_NAME);

        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setCreateTime(new Date().getTime());
        //textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        String content = (String) msgTemplate.get("content");
        String contentMerged = TemplateUtil.merge(context, content);
        textMessage.setContent(contentMerged);

        return textMessage;

//        //simulate to read from DB
//        if(MessageUtil.RESPONSEMESSAGE_SUBSCRIPTION.equals(msgName))
//        {
//            TextMessage textMessage = new TextMessage();
//            textMessage.setFromUserName(toUserName);
//            textMessage.setToUserName(fromUserName);
//
//            textMessage.setCreateTime(new Date().getTime());
//            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//            context.put("testparam", "testvalue1");
//
//            String content = "Welcome to join ${testparam} \n\n Submit 1 to check in \n Submit 2 to get agenda";
//            //String contentMerged = VelocityUtility.instance().mergeString(context, content);
//            textMessage.setContent(content);
//            return textMessage;
//
//        }
//        if(MessageUtil.RESPONSEMESSAGE_CHECKIN.equals(msgName))
//        {
//            TextMessage textMessage = new TextMessage();
//            textMessage.setFromUserName(toUserName);
//            textMessage.setToUserName(fromUserName);
//
//            textMessage.setCreateTime(new Date().getTime());
//            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//
//            String content = "Click <a href=\"http://yunyitech.duapp.com/testServlet?wechatUserID=${FromUserName}\"> here</a>";
//            String contentMerged = TemplateUtil.merge(context, content);
//            textMessage.setContent(contentMerged);
//            return textMessage;
//
//
//        }
//        if(MessageUtil.RESPONSEMESSAGE_AGENDA.equals(msgName))
//        {
//            TextMessage textMessage = new TextMessage();
//            textMessage.setFromUserName(toUserName);
//            textMessage.setToUserName(fromUserName);
//
//            textMessage.setCreateTime(new Date().getTime());
//            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//
//            String content = "Agenda command!!";
//            //String contentMerged = VelocityUtility.instance().mergeString(context, content);
//            textMessage.setContent(content);
//            return textMessage;
//
//        }
//
//        if(MessageUtil.RESPONSEMESSAGE_ERRORCOMMAND.equals(msgName))
//        {
//            context.put("param", "value1");
//            TextMessage textMessage = new TextMessage();
//            textMessage.setFromUserName(toUserName);
//            textMessage.setToUserName(fromUserName);
//
//            textMessage.setCreateTime(new Date().getTime());
//            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//
//            String content = "error command!222!${param}";
//            String contentMerged = TemplateUtil.merge(context, content);
//            textMessage.setContent(contentMerged);
//            return textMessage;
//
//        }


    }

    @Override
    public Article buildArticle(Map<String, Object> template, Map<String, Object> context) {
        Article article = new Article();
        String description = (String)template.get("description");
        if(description != null)
        {
            article.setDescription(TemplateUtil.merge(context,description));

        }

        String title = (String)template.get("title");
        if(title != null)
        {
            article.setTitle(TemplateUtil.merge(context,title));
        }

        String url = (String)template.get("url");
        if(url != null)
        {
            article.setUrl(TemplateUtil.merge(context,url));
        }

        String picUrl = (String)template.get("picUrl");
        if(picUrl != null)
        {
            article.setPicUrl(TemplateUtil.merge(context,picUrl));
        }

        return article;

    }
}

package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.Article;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.response.NewsMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-12
 * Time: PM4:48
 * To change this template use File | Settings | File Templates.
 */
public class EventQueryMessageProcessor extends AbstractMessageProcessor{

    public static Logger logger = Logger.getLogger(EventQueryMessageProcessor.class);

    private ITemplateBuilder templateBuilder = new DefaultTemplateBuilder();

    @Override
    public BaseMessage processMessage(Map<String, Object> requestMap, IConfigurationService configureService, IFacadeService facadeService) {


        logger.info("ENTER processMessage");
        String msgType = (String) requestMap.get("MsgType");

        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            String requestString = (String)requestMap.get("Content");

            if(requestString == null || requestString.trim().length() == 0)
            {
                return forwardNextProcessor(requestMap,configureService,facadeService);

            }

            if(requestString.trim().equals("1"))
            {
                logger.info("command match!");
                NewsMessage newsMessage = new NewsMessage();



                ArrayList<Article> articles = new ArrayList<Article>();

               // newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setToUserName((String)requestMap.get(MessageUtil.FROM_USER_NAME));

                newsMessage.setFromUserName((String)requestMap.get(MessageUtil.TO_USER_NAME));

                //newsMessage.setMsgType();

                Map<String, Object>  headerTmpl = configureService.getTemplate("responseMessage.event.header");

                Article headerArticle = templateBuilder.buildArticle(headerTmpl, requestMap);
                articles.add(headerArticle);

//                Article article = new Article();
//                //article.setDescription("Welcome to join HP Tech Day 2014, this is open event for everyone");
//                article.setTitle("HP CDC KM/Workshop Events");
//                //article.setUrl("http://yunyitech.duapp.com/views/images/KT2.jpg");
//                article.setPicUrl("http://yunyitech.duapp.com/views/images/KT2.jpg");
//
//                articles.add(article);

                Article article = new Article();
                article.setDescription("Welcome to join HP Tech Day 2014, this is open event for everyone");
                article.setTitle("HPIT Techday - 2014/Sep/22 ");
                article.setUrl("http://yunyitech.duapp.com/views/images/KT1.jpg");
                article.setPicUrl("http://yunyitech.duapp.com/views/images/KT1.jpg");
                articles.add(article);

                Map<String, Object>  footerTmpl = configureService.getTemplate("responseMessage.event.footer");
                Article footerArticle = templateBuilder.buildArticle(footerTmpl,requestMap);
                articles.add(footerArticle);

                newsMessage.setArticleCount(articles.size());
                newsMessage.setArticles(articles);

                logger.info("return message");
                return newsMessage;

            }



        }

        return forwardNextProcessor(requestMap,configureService,facadeService);
    }
}

package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-4
 * Time: AM7:32
 * To change this template use File | Settings | File Templates.
 */
public interface IMessageProcessor {

    public BaseMessage processMessage(Map<String,Object> requestMap, IConfigurationService configureService, IFacadeService facadeService);


    public IMessageProcessor getNextProcessor();

    public void setNextProcessor(IMessageProcessor processor);


}

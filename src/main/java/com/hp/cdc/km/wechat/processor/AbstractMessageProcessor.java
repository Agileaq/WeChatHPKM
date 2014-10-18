package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-10
 * Time: PM11:43
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractMessageProcessor implements IMessageProcessor {


    private IMessageProcessor nextProcessor = null;

    public IMessageProcessor getNextProcessor() {
        return nextProcessor;

    }

    public void setNextProcessor(IMessageProcessor processor) {

        nextProcessor = processor;

    }


    protected BaseMessage forwardNextProcessor(Map<String, Object> requestMap, IConfigurationService configureService, IFacadeService facadeService)
    {
        IMessageProcessor nextProcessor = getNextProcessor();
        if(nextProcessor != null){
            //if not null, then pass to next processor to process it.
            return nextProcessor.processMessage(requestMap,configureService,facadeService);
        }
        else
        {
            //
            return null;
        }
    }


}

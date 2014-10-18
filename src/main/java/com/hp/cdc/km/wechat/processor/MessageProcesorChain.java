package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-4
 * Time: AM7:41
 * To change this template use File | Settings | File Templates.
 *
 * This is a "simple" version of responsibity chain, it's actually not a chain since we don't care about the order
 *
 */

public class MessageProcesorChain {

    private IMessageProcessor processor = null;

    public MessageProcesorChain (IMessageProcessor... processors) {

        if(processors.length == 0)
        {
            //no any processor
            return;
        }

        // set processor to be the first processor.
        processor = processors[0];

        for(int i=1; i<processors.length; i++)
        {
            processors[i-1].setNextProcessor(processors[i]);

        }

    }

//    private static IMessageProcessor[] processors = new IMessageProcessor[] {
//
//            new SubscriberMessageProcessor(),
//            new ConfigurableCommandProcessor(),
//            new ErrorCommandProcessor()
//
//    };

    public  BaseMessage processMessage(Map<String,Object> requestMap,IConfigurationService configureService, IFacadeService facadeService)
    {
//       for(IMessageProcessor processor: processors)
//       {
//           BaseMessage message = processor.processMessage(requestMap, configureService, facadeService);
//           if(message != null )
//           {
//               return message;
//           }
//       }
//
//       return null;

        return processor.processMessage(requestMap,configureService,facadeService);

    }


}

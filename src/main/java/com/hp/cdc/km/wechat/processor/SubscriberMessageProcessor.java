package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-4
 * Time: AM7:47
 * To change this template use File | Settings | File Templates.
 */
public class SubscriberMessageProcessor extends AbstractMessageProcessor  implements IMessageProcessor {

    @Override
    public BaseMessage processMessage(Map<String, Object> requestMap, IConfigurationService configureService, IFacadeService facadeService) {
        String msgType = (String) requestMap.get("MsgType");
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
          //Event Message Type
          String eventType = (String) requestMap.get("Event");
          //it's subscriber message
          if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {


              Map<String, Object> msgTemplate = configureService.getTemplate(MessageUtil.RESPONSEMESSAGE_SUBSCRIPTION);
              if(msgTemplate == null)
              {
                  //shouldn't happen
                  throw new RuntimeException("msgTemplate is null");
              }

              //this is check in command
              ITemplateBuilder messageBuilder = new DefaultTemplateBuilder();
              return messageBuilder.buildMessage(msgTemplate, requestMap);
          }
        }

        //if run to here. it means this processor could not handle this message
        return forwardNextProcessor(requestMap,configureService,facadeService);

    }
}

package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-7
 * Time: AM11:01
 * To change this template use File | Settings | File Templates.
 *
 * This is the class to support the configurable command
 * if the mesage response is fairly simple as static response
 * we could make it configurable so that admin would be able to create command dynamically
 */

public class ConfigurableCommandProcessor extends AbstractMessageProcessor implements IMessageProcessor {


    private ITemplateBuilder templateBuilder = new DefaultTemplateBuilder();

    @Override
    public BaseMessage processMessage(Map<String, Object> requestMap, IConfigurationService configureService, IFacadeService facadeService) {

        String msgType = (String) requestMap.get("MsgType");

        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            String requestString = (String)requestMap.get("Content");

            if(requestString == null || requestString.trim().length() == 0)
            {
                //empty command, not support.
                return forwardNextProcessor(requestMap,configureService,facadeService);
            }

            //search if this command is configured
            String messageTemplateName = configureService.getMessageTemplateName(requestString);

            if(messageTemplateName == null)
            {
                //command is not configured
                //return null to report that it's not supported by this processor
                return forwardNextProcessor(requestMap,configureService,facadeService);

            }
            //now, the command is supported

            //get the configured message template
            Map<String, Object> messageTemplate = configureService.getTemplate(messageTemplateName);
            if(messageTemplate == null)
            {
                //shouldnot happen
                throw new RuntimeException("messageTemplate is null!");
            }
            BaseMessage message = templateBuilder.buildMessage(messageTemplate,requestMap);

            return message;

        }


        //if run to here. it means this processor could not handle this message
        return forwardNextProcessor(requestMap,configureService,facadeService);
        //return null;
    }
}

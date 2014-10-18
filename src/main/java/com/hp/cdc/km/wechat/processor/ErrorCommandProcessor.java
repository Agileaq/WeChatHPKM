package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.entity.HPEmployee;
import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-8
 * Time: AM8:46
 * To change this template use File | Settings | File Templates.
 */
public class ErrorCommandProcessor extends AbstractMessageProcessor implements IMessageProcessor {

    private static Logger logger = Logger.getLogger(ErrorCommandProcessor.class);



    /**
     * this method is to handle the error command, this processor should be put in the last of the processor chain.
     * @param requestMap
     * @param configureService
     * @return
     */
    @Override
    public BaseMessage processMessage(Map<String, Object> requestMap, IConfigurationService configureService, IFacadeService facadeService) {

        //added for temporary test purpose
        try
        {

            logger.info("Enter processMessage");


            Map<String, Object> msgTemplate = configureService.getTemplate(MessageUtil.RESPONSEMESSAGE_ERRORCOMMAND);
            if(msgTemplate == null)
            {
                //shouldn't happen
                throw new RuntimeException("msgTemplate is null");
            }

            //this is check in command
            ITemplateBuilder messageBuilder = new DefaultTemplateBuilder();

            //note , this processor has to be the last processor
            //so, no need to forward to other processor any more.
            return messageBuilder.buildMessage(msgTemplate, requestMap);
        }
        catch(Exception ex)
        {

            logger.error(ex);
            throw new RuntimeException(ex);
        }

    }


}

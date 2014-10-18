package com.hp.cdc.km.wechat.processor;

import com.hp.cdc.km.entity.WechatUser;
import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-11
 * Time: AM9:43
 * To change this template use File | Settings | File Templates.
 */
public class UserFilterMessageProcessor extends AbstractMessageProcessor {


    private static Logger logger = Logger.getLogger(UserFilterMessageProcessor.class);

    /**
     * Play the role as a filter
     * for each wechat message, try to get the wechat user
     *
     * @param requestMap
     * @param configureService
     * @param facadeService
     * @return
     */
    @Override
    public BaseMessage processMessage(Map<String, Object> requestMap, IConfigurationService configureService, IFacadeService facadeService) {

        logger.info("ENTER processMessage");

        String fromUserName = (String) requestMap.get(MessageUtil.FROM_USER_NAME);
        String toUserName = (String) requestMap.get(MessageUtil.TO_USER_NAME);

        logger.info("Request Message From User Name(OpenUserID):" + fromUserName);
        logger.info("Request Message To User Name:" + toUserName);

        String openUserID = fromUserName;



        //try to read the wechat user from db
        WechatUser user = facadeService.getWechatUser(openUserID);

        requestMap.put(MessageUtil.WECHAT_USER,user);


        //since this is a filter.
        //so no matter we get the wechat user or not
        //always forward to next processor to process the messaage.
        return this.forwardNextProcessor(requestMap,configureService,facadeService);

    }
}

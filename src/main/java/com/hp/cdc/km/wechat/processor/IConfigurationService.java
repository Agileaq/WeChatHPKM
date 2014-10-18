package com.hp.cdc.km.wechat.processor;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-7
 * Time: AM9:56
 * To change this template use File | Settings | File Templates.
 */
public interface IConfigurationService {


    /**
     * get the message name by command name
     * @param command
     * @return
     */
    public String getMessageTemplateName(String command);

    /**
     * read message configuration by message name
     * @param msgName
     * @return
     */
    public Map<String, Object> getTemplate(String msgName);

}

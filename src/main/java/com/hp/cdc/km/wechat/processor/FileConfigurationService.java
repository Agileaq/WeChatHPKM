package com.hp.cdc.km.wechat.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-7
 * Time: AM10:01
 * To change this template use File | Settings | File Templates.
 *
 * The Configurable is based on the java property file
 * This should be the easy or might be temporary support
 * for future, we need to create another implementation to read configuration from database
 *
 */
public class FileConfigurationService implements IConfigurationService {

    private Properties commandProperties = new Properties();

    private Properties messageProperties = new Properties();

    public FileConfigurationService ()
    {

        try {

            InputStream commandFileStream = FileConfigurationService.class.getClassLoader().getResourceAsStream("commandProcessor.properties");
            if(commandFileStream == null)
            {
                throw new RuntimeException("commandFileStream is null");
            }

            commandProperties.load(commandFileStream);

            InputStream messageFileStream = FileConfigurationService.class.getClassLoader().getResourceAsStream("msgTemplate.properties");
            if(messageFileStream == null)
            {
                throw new RuntimeException("messageFileStream is null");
            }

            messageProperties.load(messageFileStream);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }

    @Override
    public String getMessageTemplateName(String command) {

        String messageName = (String)commandProperties.get("command."+command);

        return messageName;

    }

    @Override
    public Map<String, Object> getTemplate(String templateName) {
        Map<String, Object> msgData = new HashMap<String, Object>();

        Enumeration<?> propsName = messageProperties.propertyNames();
        while(propsName.hasMoreElements())
        {
            String templateKey = (String) propsName.nextElement();
            if(templateKey.startsWith(templateName))
            {
                String mapKey= templateKey.substring(templateName.length()+1);

                Object mapValue = messageProperties.get(templateKey);

                msgData.put(mapKey, mapValue);
            }
        }

        return msgData;


//        String msgTypeKey = msgName + ".type";
//        String msgTypeValue= (String)messageProperties.get(msgTypeKey);
//
//        if(msgTypeValue == null)
//        {
//            return null;
//        }
//
//        msgData.put("type", msgTypeValue);
//
//        String msgContentKey = msgName + ".content";
//        String msgContentValue = (String)messageProperties.get(msgContentKey);
//
//        if(msgContentValue == null)
//        {
//            //shouldn't happen
//            return null;
//        }
//
//        msgData.put("content", msgContentValue);
//
//        return msgData;

    }
}

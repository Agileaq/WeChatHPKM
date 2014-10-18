package com.hp.cdc.km.wechat.processor;


import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Map;


/**
 * 核心服务类
 *
 * @author Zhou, Qi
 * @date 2013-05-20
 */
@Service
public class MessageService {

    private MessageProcesorChain messageProcesorChain = new MessageProcesorChain(
            new UserFilterMessageProcessor(),
            new SubscriberMessageProcessor(),
            new EventQueryMessageProcessor(),
            new ConfigurableCommandProcessor(),
            new ErrorCommandProcessor());


    @Resource
    private IFacadeService facadeService = null;

    private IConfigurationService configureService = new FileConfigurationService();


    public IFacadeService getFacadeService() {
        return facadeService;
    }

    public void setFacadeService(IFacadeService facadeService) {
        this.facadeService = facadeService;
    }

    /**
     * 处理微信发来的请求
     *
     * @param
     * @return
     */
    public BaseMessage processRequest(Map<String,Object> requestMap) throws Exception{
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            //String respContent = "请求处理异常，请稍候尝试！";


            BaseMessage message = messageProcesorChain.processMessage(requestMap,configureService, facadeService);


            return message;

//            if(message == null)
//            {
//                ITemplateBuilder messageBuilder = new DefaultTemplateBuilder();
//                return messageBuilder.buildMessage(MessageUtil.RESPONSEMESSAGE_ERRORCOMMAND, requestMap);
//            }
//            else
//            {
//               return message;
//            }
//
//            // 发送方帐号（open_id）
//            String fromUserName = requestMap.get("FromUserName");
//            // 公众帐号
//            String toUserName = requestMap.get("ToUserName");
//            // 消息类型
//            String msgType = requestMap.get("MsgType");
//
//
//
//            // 文本消息
//            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
//                respContent = "您发送的是文本消息！";
//            }
//            // 图片消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//                respContent = "您发送的是图片消息！";
//            }
//            // 地理位置消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
//                respContent = "您发送的是地理位置消息！";
//            }
//            // 链接消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
//                respContent = "您发送的是链接消息！";
//            }
//            // 音频消息
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
//                respContent = "您发送的是音频消息！";
//            }
//            // 事件推送
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
//                // 事件类型
//                String eventType = requestMap.get("Event");
//                // 订阅
//                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
//                    respContent = "谢谢您的关注！";
//                }
//                // 取消订阅
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
//                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
//                }
//                // 自定义菜单点击事件
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
//                    // TODO 自定义菜单权没有开放，暂不处理该类消息
//                }
//            }
//
//            textMessage.setContent(respContent);
//            return textMessage;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }


    }
}

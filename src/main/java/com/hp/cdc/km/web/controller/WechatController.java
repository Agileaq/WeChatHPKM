package com.hp.cdc.km.web.controller;

import com.hp.cdc.km.web.entity.JsonResult;
import com.hp.cdc.km.wechat.processor.MessageService;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;
import com.hp.cdc.km.wechat.util.SignUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-10
 * Time: PM5:59
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class WechatController {

    @Resource
    private MessageService messageService = null;
    
    final static Logger logger =Logger.getLogger(WechatController.class);
    
    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value="/cdckmWechat",method= RequestMethod.GET)
    @ResponseBody
    public String signin(String openId,String empId, String emailAddress,HttpServletRequest request,HttpServletResponse response){
    	logger.info(empId);
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        //PrintWriter out = response.getWriter();
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        else {
            return "error";
        }
    }


    @RequestMapping(value="/cdckmWechat",method= RequestMethod.POST)
    @ResponseBody
    public String doMessage(String openId,String empId, String emailAddress,HttpServletRequest request,HttpServletResponse response){
        try
        {

        	logger.info("ENTER doMessage");
        	
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> requestMap = MessageUtil.parseXml(request);


            BaseMessage message = messageService.processRequest(requestMap);
            String responseMsg = message.toXML();

            logger.info("Response: " + responseMsg);

            return responseMsg;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        finally{

        }
    }


}

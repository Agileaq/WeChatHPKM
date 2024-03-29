package com.hp.cdc.km.wechat;

import com.hp.cdc.km.wechat.processor.MessageService;
import com.hp.cdc.km.wechat.response.BaseMessage;
import com.hp.cdc.km.wechat.util.MessageUtil;
import com.hp.cdc.km.wechat.util.SignUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 核心请求处理类
 *
 * @author liufeng
 * @date 2013-05-18
 */
public class CoreServlet extends HttpServlet {

    private  MessageService messageService = new MessageService();

    private static final long serialVersionUID = 4440739483644821986L;

    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 消息的接收、处理、响应

        // xml请求解析
        PrintWriter out = null;
        try
        {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> requestMap = MessageUtil.parseXml(request);


            BaseMessage message = messageService.processRequest(requestMap);

            out = response.getWriter();
            out.print(message.toXML());

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally{
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch(Exception ex)
                {}
            }
        }

    }

}


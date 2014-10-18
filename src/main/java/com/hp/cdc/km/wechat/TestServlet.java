package com.hp.cdc.km.wechat;

import com.hp.cdc.km.wechat.util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-6
 * Time: PM8:09
 * To change this template use File | Settings | File Templates.
 */
public class TestServlet  extends HttpServlet {

    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           String openID = request.getParameter("wechatUserID");
           PrintWriter writer = response.getWriter();

           writer.print("openid:" + openID);

           writer.close();


    }


}

package com.hp.cdc.km.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.web.entity.JsonResult;


/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhongl
 * Date: 9/5/14
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController {
    final static Logger logger = Logger.getLogger(LoginController.class);
    
    @Autowired
    private IFacadeService service; 
    
    
    @RequestMapping(value="/login",method= RequestMethod.POST,produces="application/json")
    @ResponseBody
      public JsonResult login(String openUserId,String empId, String emailAddress,HttpServletRequest request,HttpServletResponse response){
    	logger.debug("open id: " + openUserId);
    	logger.debug("employee id: "+empId);
    	Integer result = service.login(openUserId, empId, emailAddress);    	
        JsonResult jsonResult = new JsonResult(result);
        return jsonResult;
      }

}

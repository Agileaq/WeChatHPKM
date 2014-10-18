package com.hp.cdc.km.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.web.entity.JsonResult;

/**
 * Created with IntelliJ IDEA. WechatUser: zhongl Date: 9/10/14 Time: 2:44 PM To
 * change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/checkin")
public class CheckInController {

	@Autowired
	private IFacadeService service;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public JsonResult<Integer> checkin (String eventScheduleId, String openUserID) {
		Integer  result  = service.checkin(eventScheduleId, openUserID);
		JsonResult<Integer> jsonResult = new JsonResult<Integer>(
				result);
		return jsonResult;

	}
	

}

package com.hp.cdc.km.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.cdc.km.entity.EventScheduler;
import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.web.entity.JsonResult;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhongl
 * Date: 9/5/14
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/agenda")
public class AgendaController {

	 @Autowired
	 private IFacadeService service; 
	    
	 

    @RequestMapping(value="/locations",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<List<String>> getAgendaLocationsByEventScheduleId(String eventScheduleId){
    	List<String> locations = service.getAgendaLocationsByEventScheduleId(eventScheduleId);
    	JsonResult<List<String>> result = new JsonResult<List<String>>(locations);
    	return result;
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult< List<EventScheduler>> getAgendaByLocationNEvent(String eventId, String location) {
    	List<EventScheduler> shedulers = service.getAgendaByLocationNEvent(eventId, location);
    	JsonResult<List<EventScheduler>> result = new JsonResult<List<EventScheduler>>(shedulers);
		return result;
	}
 

}

package com.hp.cdc.km.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hp.cdc.km.entity.CheckIn;
import com.hp.cdc.km.entity.Event;
import com.hp.cdc.km.service.IFacadeService;
import com.hp.cdc.km.web.entity.JsonResult;

@RequestMapping("/event")
public class EventController {
	 
    @Autowired
    private IFacadeService service; 
    
    @RequestMapping(value="/recent",method=RequestMethod.GET)
	public JsonResult<List<Event>> getRecentEvents() {
    	List<Event> events = service.getRecentEvents();
    	JsonResult<List<Event>> result = new JsonResult<List<Event>>(events);
		return result;
	}
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
	public JsonResult<Event> getEvent(@PathVariable String id) {
    	Event event = service.getEvent(id);
    	JsonResult<Event> result = new JsonResult<Event>(event);
		return result;
	}
    
    @RequestMapping(value="/checkins",method=RequestMethod.GET)
	public JsonResult<List<CheckIn>> getEventCheckIn(String eventId,
			String openUserID) {
		List<CheckIn> checkins = service.getEventCheckIn(eventId, openUserID);
		JsonResult<List<CheckIn>> result = new JsonResult<List<CheckIn>>(
				checkins);
		return result;

	}
    
    
}

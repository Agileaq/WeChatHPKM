package com.hp.cdc.km.service;

import java.util.List;

import com.hp.cdc.km.entity.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-10
 * Time: PM10:48
 * To change this template use File | Settings | File Templates.
 */
public interface IFacadeService {
	/**
	 * get user info, like email, employeeId by openUserId
	 * @param openUserID
	 * @return
	 */
    public WechatUser getWechatUser(String openUserID);

    /**
     * find event list after current time
     * @return
     */
    List<Event> getRecentEvents();
    
    /**
     * find event by event id
     * @param id
     * @return
     */
    Event getEvent(String id);
    
    /**
     * find all check in records under an event
     * @param eventId
     * @param openUserID
     * @return
     */
    List<CheckIn> getEventCheckIn(String eventId, String openUserID);
    
    /**
     *  find location list of a event schedule
     * @param eventScheduleId
     * @return
     */
    List<String> getAgendaLocationsByEventScheduleId(String eventScheduleId);

    /**
     * find location's agenda list of a event 
     * @param eventScheduleId
     * @param agendaLocation
     * @return  List<EventScheduler> list, every EventScheduler contains a collection of agenda
     */
    List<EventScheduler> getAgendaByLocationNEvent(String eventId, String location);
 
    
    /**
     * 
     * @param eventScheduleId
     * @param openUserID
     * @return 0: check in successfully 		  
     *         1: no registration, cannot check in
     *         2: this wechat user's hp email account is not established in DB.
     *         3: the user checkin at a wrong time point
     */		
    int checkin(String eventScheduleId, String openUserID);
    
    /**
     * 
     * @param openUserID
     * @param empId
     * @param emailAddress
     * @return 0: login successfully
     * 		   1: empId or emailAddress is not match
     * 		   2: no registration information
     * 			
     */
    int login(String openUserID,String empId,String emailAddress);


    /**
     *
     * @param employee
     */
    void createEmployee(HPEmployee employee);
    
    
}

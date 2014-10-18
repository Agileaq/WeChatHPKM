package com.hp.cdc.km.web.controller;

import com.hp.cdc.km.dao.IEventDao;
import com.hp.cdc.km.dao.IEventSchedulerDao;
import com.hp.cdc.km.dao.IHpEmployeeDao;
import com.hp.cdc.km.entity.Event;
import com.hp.cdc.km.entity.EventScheduler;
import com.hp.cdc.km.entity.HPEmployee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-15
 * Time: PM8:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class InitDataController {
    private static Logger logger = Logger.getLogger(InitDataController.class);

    @Resource
    private IHpEmployeeDao hpEmployeeDao;

    @Resource
    private IEventDao eventDao;


    @Resource
    private IEventSchedulerDao eventSchedulerDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        InitDataController.logger = logger;
    }

    public IEventDao getEventDao() {
        return eventDao;
    }

    public void setEventDao(IEventDao eventDao) {
        this.eventDao = eventDao;
    }

    public IHpEmployeeDao getHpEmployeeDao() {
        return hpEmployeeDao;
    }

    public void setHpEmployeeDao(IHpEmployeeDao hpEmployeeDao) {
        this.hpEmployeeDao = hpEmployeeDao;
    }

    @RequestMapping(value="/createEmployee/{eid}",method= RequestMethod.GET)
    @ResponseBody
    public String createEmployee(@PathVariable(value = "eid") String eid){
        try
        {
            logger.info("ENTER createEmployee");
            HPEmployee employee = new HPEmployee();

            HPEmployee searchEmployee = hpEmployeeDao.findByEid(eid);
            if(searchEmployee != null)
            {
                return "EID already existed!";
            }


            employee.setEid(eid);

            hpEmployeeDao.save(employee);
            return "Saved successfully";
        }
        catch(Exception ex) {

            logger.error(ex);

            return "ERROR: " + ex.toString();

        }

    }

    @RequestMapping(value="/searchEventScheduler/{eventSchedulerID}",method= RequestMethod.GET)
    @ResponseBody
    public EventScheduler searchEventScheduler(@PathVariable(value = "eventSchedulerID") String eventSchedulerID) {
        return eventSchedulerDao.findById(eventSchedulerID);

    }

    @RequestMapping(value="/searchEvent/{eventID}",method= RequestMethod.GET)
    @ResponseBody
    public Event searchEvent(@PathVariable(value = "eventID") String eventID) {

        return eventDao.findById(eventID);
//        Event event = new Event();
//        event.setEventName("This is a test event");
//        event.setCheckInNeedRegistration(true);
//        event.setEventDescription("This event is for a test purpose, only for test");
//        event.setPresenter("zhou, qi");
//        event.setId("11233");
//
//
//
//
//        EventScheduler scheduler = new EventScheduler();
//        scheduler.setId("123");
//        scheduler.setEndTime(new Date());
//        scheduler.setPresenter("zhou, qi");
//        scheduler.setEvent(event);
//        //event.getSchedulers().add(scheduler);
//
//        eventSchedulerDao.save(scheduler);

        //return event;
    }


    @RequestMapping(value="/createEvent",method= RequestMethod.POST)
    @ResponseBody
    public String createEvent(@RequestBody Event event){
        try
        {
            logger.info("ENTER createEvent");

            Collection<EventScheduler> schedulers = event.getSchedulers();

            for(EventScheduler scheduler: schedulers)
            {
                scheduler.setEvent(event);
                eventSchedulerDao.save(scheduler);

            }
            event.setSchedulers(null);
            eventDao.save(event);

            return "Saved successfully";
        }
        catch(Exception ex) {

            logger.error(ex);

            return "ERROR: " + ex.toString();

        }

    }




}

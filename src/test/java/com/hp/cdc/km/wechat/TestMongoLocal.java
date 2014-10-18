package com.hp.cdc.km.wechat;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hp.cdc.km.dao.IAgendaDao;
import com.hp.cdc.km.dao.ICheckInDao;
import com.hp.cdc.km.dao.IEventDao;
import com.hp.cdc.km.dao.IEventSchedulerDao;
import com.hp.cdc.km.dao.IHpEmployeeDao;
import com.hp.cdc.km.dao.IRegistrationDao;
import com.hp.cdc.km.dao.IWechatUserDao;
import com.hp.cdc.km.entity.Agenda;
import com.hp.cdc.km.entity.HPEmployee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class TestMongoLocal {

    @Resource
    IHpEmployeeDao employeeDao = null;

    @Resource
    IAgendaDao agendaDao = null;
    @Resource
    ICheckInDao checkInDao = null;
    @Resource
    IEventDao eventDao = null;
    @Resource
    IEventSchedulerDao eventSchedulerDao = null;
    @Resource
    IRegistrationDao registrationDao = null;
    @Resource
    IWechatUserDao wechatUserDao = null;

    public IHpEmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(IHpEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /*
     * @Before public void setup(){ mongo. }
     */

    /**
     * @return the agendaDao
     */
    public final IAgendaDao getAgendaDao() {
        return agendaDao;
    }

    /**
     * @param agendaDao
     *            the agendaDao to set
     */
    public final void setAgendaDao(IAgendaDao agendaDao) {
        this.agendaDao = agendaDao;
    }

    /**
     * @return the checkInDao
     */
    public final ICheckInDao getCheckInDao() {
        return checkInDao;
    }

    /**
     * @param checkInDao
     *            the checkInDao to set
     */
    public final void setCheckInDao(ICheckInDao checkInDao) {
        this.checkInDao = checkInDao;
    }

    /**
     * @return the eventDao
     */
    public final IEventDao getEventDao() {
        return eventDao;
    }

    /**
     * @param eventDao
     *            the eventDao to set
     */
    public final void setEventDao(IEventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * @return the eventSchedulerDao
     */
    public final IEventSchedulerDao getEventSchedulerDao() {
        return eventSchedulerDao;
    }

    /**
     * @param eventSchedulerDao
     *            the eventSchedulerDao to set
     */
    public final void setEventSchedulerDao(IEventSchedulerDao eventSchedulerDao) {
        this.eventSchedulerDao = eventSchedulerDao;
    }

    /**
     * @return the registrationDao
     */
    public final IRegistrationDao getRegistrationDao() {
        return registrationDao;
    }

    /**
     * @param registrationDao
     *            the registrationDao to set
     */
    public final void setRegistrationDao(IRegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    /**
     * @return the wechatUserDao
     */
    public final IWechatUserDao getWechatUserDao() {
        return wechatUserDao;
    }

    /**
     * @param wechatUserDao
     *            the wechatUserDao to set
     */
    public final void setWechatUserDao(IWechatUserDao wechatUserDao) {
        this.wechatUserDao = wechatUserDao;
    }

    @Test
    @Ignore
    public void testSpringMongo() {
        HPEmployee employee = new HPEmployee();

        employee.setEid("20286938");

        employeeDao.save(employee);
    }

    // private String id;
    //
    // private String topic;
    //
    // private String presenter;
    //
    // private String location;
    //
    // private Date startTime;
    // private Date endTime;
    //
    // private EventScheduler scheduler;

    public void testSpringAgenda() {

        Agenda agenda = new Agenda();
        agenda.setId("1");
        agenda.setTopic("HP CDC KM/Workshop Event");
        agenda.setLocation("HP_VIA_A201");

        agenda.setStartTime(new Date());

        Date dt = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = cal.getTime();
        agenda.setEndTime(endDate);

        agendaDao.save(agenda);
    }
}

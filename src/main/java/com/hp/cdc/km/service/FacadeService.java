package com.hp.cdc.km.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hp.cdc.km.dao.IAgendaDao;
import com.hp.cdc.km.dao.ICheckInDao;
import com.hp.cdc.km.dao.IEventDao;
import com.hp.cdc.km.dao.IEventSchedulerDao;
import com.hp.cdc.km.dao.IHpEmployeeDao;
import com.hp.cdc.km.dao.IRegistrationDao;
import com.hp.cdc.km.dao.IWechatUserDao;
import com.hp.cdc.km.entity.Agenda;
import com.hp.cdc.km.entity.CheckIn;
import com.hp.cdc.km.entity.Event;
import com.hp.cdc.km.entity.EventScheduler;
import com.hp.cdc.km.entity.HPEmployee;
import com.hp.cdc.km.entity.Registration;
import com.hp.cdc.km.entity.WechatUser;
import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-10
 * Time: PM9:22
 * To change this template use File | Settings | File Templates.
 * This is the facade service which will wrap all the operations
 *
 */
@Service
public class FacadeService implements IFacadeService {

    @Resource
    private IHpEmployeeDao employeeDao;
    @Resource
    private IAgendaDao agendaDao;
    @Resource
    private ICheckInDao checkInDao;
    @Resource
    private IEventDao eventDao;
    @Resource
    private IEventSchedulerDao eventSchedulerDao;
    @Resource
    private IRegistrationDao registrationDao;
    @Resource
    private IWechatUserDao wechatUserDao;

    public void createEmployee(HPEmployee employee) {
        // save to employee table
        employeeDao.save(employee);
    }

    public WechatUser getWechatUser(String openUserID) {
        // should read from database
        WechatUser wechatUser = new WechatUser();
        

        wechatUser.setOpenUserID(openUserID);

        return wechatUser;

    }

    @Override
    public List<Event> getRecentEvents() {
    	Date date = new Date();
        return eventDao.findAll();
    }

    @Override
    public Event getEvent(String id) {
        return eventDao.findById(id);
    }

    @Override
    public List<CheckIn> getEventCheckIn(String eventId, String openUserID) {

        List<CheckIn> checkInFinally = new ArrayList<CheckIn>();
        // find we char user by openUserID
        WechatUser wechatUser = wechatUserDao.findByOpenUserID(openUserID);
        if (wechatUser != null) {
            HPEmployee HPEmployee = wechatUser.getBindingEmployee();
            if (HPEmployee != null) {
                List<CheckIn> checkIns = checkInDao.findCheckInsByHPEmployee(HPEmployee.getId());
                if (checkIns != null && !checkIns.isEmpty()) {
                    for (CheckIn checkIn : checkIns) {
                        EventScheduler eventScheduler = checkIn.getEventScheduler();
                        if (eventScheduler != null) {
                            if (eventId.equalsIgnoreCase(eventScheduler.getId())) {
                                checkInFinally.add(checkIn);
                            }
                        }
                    }
                }
            }
        }
        return checkInFinally;
    }

    @Override
    public List<String> getAgendaLocationsByEventScheduleId(String eventScheduleId) {

        List<String> locations = new ArrayList<String>();
        EventScheduler eventScheduler = eventSchedulerDao.findById(eventScheduleId);
        Collection<Agenda> agendas = eventScheduler.getAgendas();
        if (agendas != null && !agendas.isEmpty()) {
            for (Agenda agenda : agendas) {
                String location = agenda.getLocation();
                locations.add(location);
            }
        }
        return locations;
    }

	@Override
	public List<EventScheduler> getAgendaByLocationNEvent(String eventId, String location) {
		Event event = eventDao.findById(eventId);
		return eventSchedulerDao.findByLocationAndEvent(event, location);
	}

	@Override
	public int checkin(String eventScheduleId, String openUserID) {
		CheckIn checkIn = new CheckIn();
		EventScheduler eventScheduler = eventSchedulerDao.findById(eventScheduleId);
		Event event = eventScheduler.getEvent();
		
		HPEmployee attendee = wechatUserDao.findByOpenUserID(openUserID).getBindingEmployee();
		
		if (attendee == null) {
			return 2;
		}
		
		if (event.isCheckInNeedRegistration()) {
			Registration registration = registrationDao.findByEventAndHPEmployee(event, attendee);
			if (registration == null) {
				return 1;
			}			
		}
	 
		long startTime = (eventScheduler.getStartTime().getTime() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000);
		long endTime = eventScheduler.getEndTime().getTime();
		long currentT = Calendar.getInstance().getTimeInMillis();
		
		if(currentT < startTime || currentT > endTime) {
			return 3;
		}
		
		checkIn.setCheckinTime(Calendar.getInstance().getTime());
		checkIn.setEmployee(attendee);
		checkIn.setEventScheduler(eventScheduler);		
		checkInDao.save(checkIn);
		
		return 0;
	}

	@Override
	public int login(String openUserID, String empId, String emailAddress) {
		Date currentTime = Calendar.getInstance().getTime();

		HPEmployee hpEmployee = new HPEmployee();
		hpEmployee.setEid(empId);
		hpEmployee.setLastModifiedDate(currentTime);
		
		WechatUser wcUser = new WechatUser();

		WechatUser wechatUser = wechatUserDao.findByOpenUserID(openUserID);
		if(wechatUser == null) {
			hpEmployee.setCreationDate(currentTime);
			employeeDao.saveOrUpdate(hpEmployee);
			wcUser.setCreationDate(currentTime);
			wcUser.setSubscriptionDate(currentTime);
		}
		
		List<Registration> regs0 = registrationDao.findByHPEmployee(hpEmployee);
		List<Registration> regs1 = registrationDao.findByHPEmployeeAndHPEmail(hpEmployee, emailAddress);

		if (regs0 == null || regs0.isEmpty()) {
			return 2;
		} else if ((regs0 != null && !regs0.isEmpty())  && (regs1 == null || regs1.isEmpty())) {
			return 1;
		}		
		
		wcUser.setBindingEmployee(hpEmployee);
		wcUser.setEmail(emailAddress);
		wcUser.setLastModifiedDate(currentTime);
		wcUser.setOpenUserID(openUserID);
		
		wechatUserDao.saveOrUpdate(wcUser);
		
		return 0;
	}

	
	
}

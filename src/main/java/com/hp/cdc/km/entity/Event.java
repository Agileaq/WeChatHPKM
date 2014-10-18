package com.hp.cdc.km.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-11
 * Time: PM3:04
 * To change this template use File | Settings | File Templates.
 * This is the event activity
 *
 */
@Document(collection = "event")
public class Event extends Entity{

    /**
     * Unique generated event ID;
     */
	@Id
    private String id;

    private String eventName;

    private String eventDescription;

    private boolean checkInNeedRegistration;
    
    private String presenter;

    private String location;

    /**
     * empty means it's unsecured mode
     */
    private String secureKey;


    private Collection<EventScheduler> schedulers = new ArrayList<EventScheduler>();


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public String getEventDescription() {
		return eventDescription;
	}


	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}


	public boolean isCheckInNeedRegistration() {
		return checkInNeedRegistration;
	}


	public void setCheckInNeedRegistration(boolean checkInNeedRegistration) {
		this.checkInNeedRegistration = checkInNeedRegistration;
	}


	public String getPresenter() {
		return presenter;
	}


	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getSecureKey() {
		return secureKey;
	}


	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}


	public Collection<EventScheduler> getSchedulers() {
		return schedulers;
	}


	public void setSchedulers(Collection<EventScheduler> schedulers) {
		this.schedulers = schedulers;
	}

}

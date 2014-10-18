package com.hp.cdc.km.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-11
 * Time: PM4:34
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "checkin")
@CompoundIndexes({
        @CompoundIndex(name = "checkin_idx1", def = "{'employee': 1, 'eventScheduler': 1}" , unique=true)
})
public class CheckIn {

    /**
     * unique identifier
     */
	@Id
    private String id;

    @DBRef
    private EventScheduler eventScheduler;

    @DBRef
    private HPEmployee employee;
    
    private Date checkinTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

	public EventScheduler getEventScheduler() {
		return eventScheduler;
	}

	public void setEventScheduler(EventScheduler eventScheduler) {
		this.eventScheduler = eventScheduler;
	}

	public HPEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(HPEmployee employee) {
		this.employee = employee;
	}
}

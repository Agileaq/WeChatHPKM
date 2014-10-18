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
 * Time: PM4:23
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "registration")
@CompoundIndexes({
        @CompoundIndex(name = "registration_idx1", def = "{'attendee': 1, 'event': 1}" , unique=true)
})
public class Registration extends Entity{


    /**
     * unique identifier
     */
	@Id
    private String id;

    @DBRef
    private HPEmployee attendee;

    @DBRef
    private Event event;

    private String hpEmail;

    private Date registrationDate;

    public String getHpEmail() {
        return hpEmail;
    }

    public void setHpEmail(String hpEmail) {
        this.hpEmail = hpEmail;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HPEmployee getAttendee() {
        return attendee;
    }

    public void setAttendee(HPEmployee attendee) {
        this.attendee = attendee;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}

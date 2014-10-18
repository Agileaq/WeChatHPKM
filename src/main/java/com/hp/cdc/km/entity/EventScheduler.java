package com.hp.cdc.km.entity;

import java.util.Collection;
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
 * Time: PM3:13
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "eventScheduler")

public class EventScheduler extends Entity{

    //unique generated ID;
	@Id
    private String id;

    @DBRef
    private Event event;

    private Date startTime;

    private Date endTime;

    private String location;

    private String presenter;

    private Collection<Agenda> agendas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public Collection<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(Collection<Agenda> agendas) {
        this.agendas = agendas;
    }
}

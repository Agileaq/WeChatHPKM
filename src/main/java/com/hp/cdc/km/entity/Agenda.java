package com.hp.cdc.km.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA. User: zhouqi Date: 14-9-11 Time: PM4:15 To change this template use
 * File | Settings | File Templates.
 */
@Document(collection = "agenda")
@CompoundIndexes({ @CompoundIndex(name = "agenda_idx1", def = "{'eventScheduler': 1}", unique = true) })
public class Agenda extends Entity {

    /**
     * unique identifier
     */
    @Id
    private String id;

    private String topic;

    private String presenter;

    private String location;

    private Date startTime;
    private Date endTime;

    @DBRef
    private EventScheduler scheduler;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
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

}

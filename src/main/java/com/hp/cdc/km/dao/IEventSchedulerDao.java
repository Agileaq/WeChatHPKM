package com.hp.cdc.km.dao;

import java.util.List;

import com.hp.cdc.km.entity.Event;
import com.hp.cdc.km.entity.EventScheduler;

public interface IEventSchedulerDao  extends ICommonDao<EventScheduler> {
	List<EventScheduler> findByLocationAndEvent(Event event, String location);

}

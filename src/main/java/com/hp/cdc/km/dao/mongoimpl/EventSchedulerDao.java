package com.hp.cdc.km.dao.mongoimpl;

import org.springframework.stereotype.Component;

import com.hp.cdc.km.dao.IEventSchedulerDao;
import com.hp.cdc.km.entity.EventScheduler;
import com.hp.cdc.km.entity.Event;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Component
public class EventSchedulerDao extends CommonMongoDAO<EventScheduler> implements IEventSchedulerDao {

	
	@Override
	public List<EventScheduler> findByLocationAndEvent(Event event, String location) {		
	   
		List<EventScheduler> matchedEventSchedulers = findAllByQuery(new Query(Criteria.where("location").is(location)
				.andOperator(Criteria.where("event").is(event))));
		return matchedEventSchedulers;
	}

}

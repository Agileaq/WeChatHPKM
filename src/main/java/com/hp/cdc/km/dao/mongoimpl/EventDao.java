package com.hp.cdc.km.dao.mongoimpl;

import org.springframework.stereotype.Component;

import com.hp.cdc.km.dao.IEventDao;
import com.hp.cdc.km.entity.Event;
@Component
public class EventDao   extends CommonMongoDAO<Event> implements IEventDao {

}

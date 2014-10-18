package com.hp.cdc.km.dao.mongoimpl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hp.cdc.km.dao.IRegistrationDao;
import com.hp.cdc.km.entity.Registration;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hp.cdc.km.entity.Event;
import com.hp.cdc.km.entity.HPEmployee;
@Component
public class RegistrationDao extends CommonMongoDAO<Registration> implements IRegistrationDao {

	@Override
	public Registration findByEventAndHPEmployee(Event event,
			HPEmployee hpEmployee) {
		
		return findOneByQuery(new Query(Criteria.where("event").is(event)
				.andOperator(Criteria.where("attendee").is(hpEmployee))));
	}

	@Override
	public List<Registration> findByHPEmployeeAndHPEmail(HPEmployee hpEmployee,
			String email) {
		return findAllByQuery(new Query(Criteria.where("attendee").is(hpEmployee)
				.andOperator(Criteria.where("hpEmail").is(email))));
	}

	@Override
	public List<Registration> findByHPEmployee(HPEmployee hpEmployee) {
		return findAllByQuery(new Query(Criteria.where("attendee").is(hpEmployee)));
	}

}

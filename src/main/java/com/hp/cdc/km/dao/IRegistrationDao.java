package com.hp.cdc.km.dao;

import java.util.List;

import com.hp.cdc.km.entity.Event;
import com.hp.cdc.km.entity.HPEmployee;
import com.hp.cdc.km.entity.Registration;

public interface IRegistrationDao  extends ICommonDao<Registration> {
	
	Registration findByEventAndHPEmployee(Event event, HPEmployee hpEmployee);
	List<Registration> findByHPEmployeeAndHPEmail(HPEmployee hpEmployee, String email);
	List<Registration> findByHPEmployee(HPEmployee hpEmployee);

}

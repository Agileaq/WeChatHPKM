package com.hp.cdc.km.dao.mongoimpl;

import org.springframework.stereotype.Component;

import com.hp.cdc.km.dao.IAgendaDao;
import com.hp.cdc.km.entity.Agenda;
@Component
public class AgendaDao extends CommonMongoDAO<Agenda> implements IAgendaDao {

}

package com.hp.cdc.km.dao.mongoimpl;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.hp.cdc.km.dao.ICheckInDao;
import com.hp.cdc.km.entity.CheckIn;

@Component
public class CheckInDao extends CommonMongoDAO<CheckIn> implements ICheckInDao {

    @Override
    public List<CheckIn> findCheckInsByHPEmployee(String hpEmployeeID) {

        return findAllByQuery(new Query(Criteria.where("hpEmployeeID").is(hpEmployeeID)));
    }

}

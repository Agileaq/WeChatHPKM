package com.hp.cdc.km.dao.mongoimpl;

import com.hp.cdc.km.dao.IHpEmployeeDao;
import com.hp.cdc.km.entity.HPEmployee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-15
 * Time: AM10:04
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HpEmployeeDao extends CommonMongoDAO<HPEmployee> implements IHpEmployeeDao {
    @Override
    public HPEmployee findByEid(String eid) {
        return this.findOneByQuery(new Query(Criteria.where("eid").is(eid)));

    }
}


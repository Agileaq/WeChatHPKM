package com.hp.cdc.km.dao.mongoimpl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.hp.cdc.km.dao.IWechatUserDao;
import com.hp.cdc.km.entity.WechatUser;

@Component
public class WechatUserDao  extends CommonMongoDAO<WechatUser> implements IWechatUserDao {
	
	@Override
	public WechatUser findByOpenUserID(String openUserID) {
		
		return findOneByQuery(new Query(Criteria.where("openUserID").is(openUserID)));
	}

}

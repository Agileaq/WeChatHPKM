package com.hp.cdc.km.dao;

import com.hp.cdc.km.entity.WechatUser;

public interface IWechatUserDao extends ICommonDao<WechatUser> {
	
	WechatUser findByOpenUserID(String openUserID);

}

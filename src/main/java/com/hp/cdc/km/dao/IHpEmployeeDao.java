package com.hp.cdc.km.dao;

import com.hp.cdc.km.entity.HPEmployee;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-15
 * Time: AM10:01
 * To change this template use File | Settings | File Templates.
 */
public interface IHpEmployeeDao extends ICommonDao<HPEmployee> {

    public HPEmployee findByEid(String eid);


}

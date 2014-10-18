package com.hp.cdc.km.dao;

import java.util.List;
import com.hp.cdc.km.entity.CheckIn;

public interface ICheckInDao extends ICommonDao<CheckIn> {

    List<CheckIn> findCheckInsByHPEmployee(String hpEmployeeID);

}

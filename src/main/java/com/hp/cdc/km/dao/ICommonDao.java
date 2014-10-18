package com.hp.cdc.km.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-15
 * Time: AM9:54
 * To change this template use File | Settings | File Templates.
 */
public interface ICommonDao<T> {

    public void save(T object);

    public void saveOrUpdate(T object);

    public List<T> findAll();

    public T findById(String id);

    public void remove(T obj);


}


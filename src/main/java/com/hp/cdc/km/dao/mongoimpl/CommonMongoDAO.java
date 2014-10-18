package com.hp.cdc.km.dao.mongoimpl;

import com.hp.cdc.km.dao.ICommonDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-15
 * Time: AM9:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class CommonMongoDAO<T> implements ICommonDao<T> {

    private Class entityClass;

    public CommonMongoDAO()
    {
        initiateEntityClass();
    }

    @Resource(name="mongoTemplate")
    private MongoTemplate mongoTemplate;


    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    private void initiateEntityClass()
    {
        ParameterizedType genericSuperclass = (ParameterizedType)getClass().getGenericSuperclass();

        Type type = genericSuperclass.getActualTypeArguments()[0];

//        if (type instanceof ParameterizedType)
//        {
//            this.entityClass = (Class<T>)((ParameterizedType)type).getRawType();
//        }
//        else
//        {
        this.entityClass = (Class<T>)type;
//        }

    }

    public void save(T obj)
    {
        mongoTemplate.insert(obj);

    }


    public void saveOrUpdate(T obj)
    {
        mongoTemplate.save(obj);

    }
    public List<T> findAll() {

        return (List<T>) mongoTemplate.findAll(entityClass);
    }


    public T findById(String id)
    {
        Query query = new Query(Criteria.where("_id").is(id));
        return (T)mongoTemplate.findOne(query, entityClass);

    }

    public void remove(T obj)
    {
        mongoTemplate.remove(obj);
    }
    protected List<T> findAllByQuery(Query query)
    {
        return mongoTemplate.find(query, entityClass);

    }

    protected T findOneByQuery(Query query){
        return (T) mongoTemplate.findOne(query, entityClass);
    }

    protected long getCount(Query query) {
        return mongoTemplate.count(query, entityClass);
    }
}

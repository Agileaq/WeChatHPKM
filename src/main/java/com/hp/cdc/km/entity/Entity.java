package com.hp.cdc.km.entity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhouqi
 * Date: 14-9-11
 * Time: PM4:26
 * To change this template use File | Settings | File Templates.
 */
public class Entity {

    private Date creationDate;
    private Date lastModifiedDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

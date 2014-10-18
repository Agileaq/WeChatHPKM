package com.hp.cdc.km.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-10
 * Time: PM10:35
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "hpEmployee")
public class HPEmployee extends Entity{


    /**
     * the unique id of the HP Employee
     */
	@Id
    private String id;

    @Indexed(unique=true)
    private String eid;

    //since it's possible that user could change their email address
    //move the email adddress to registration

    //private String hpEmail;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }


}

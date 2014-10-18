package com.hp.cdc.km.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * WechatUser: zhouqi
 * Date: 14-9-10
 * Time: PM9:56
 * To change this template use File | Settings | File Templates.
 */
@Document(collection = "wechatUser")
public class WechatUser extends Entity{


    //the unique id of the Wechatuser
	@Id
    private String id;


    @DBRef
    private HPEmployee bindingEmployee;

    /**
     * The open user ID generated by Wechat system.
     */
    @Indexed(unique=true)
    private String openUserID;

    /**
     * This should be the email for the wechat account
     * at this moment, since we do not pay for the service account
     * we are not able to get the email address
     * leave it for future usage.
     */
    private String email;


    private Date subscriptionDate;

    /**
     * wechat user name
     *  we are not able to get it, the same reason as email
     * but we want to keep it for future usage.
     */
    private String userName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenUserID() {
        return openUserID;
    }

    public void setOpenUserID(String openUserID) {
        this.openUserID = openUserID;
    }


    public HPEmployee getBindingEmployee() {
        return bindingEmployee;
    }

    public void setBindingEmployee(HPEmployee bindingEmployee) {
        this.bindingEmployee = bindingEmployee;
    }
}
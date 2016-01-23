package com.wengyingjian.kylin.rabbitmq;

import java.io.Serializable;

/**
 * Created by wengyingjian on 16/1/23.
 */
public class MessageBean implements Serializable {
    //推送给移动端或者PC端:mobile/PC
    private String key;

    //单个推送或者群体推送:single/multiple
    private String type;

    //移动端推送app名字:store/dorm
    private String target;

    //推送内容
    private String content;

    //单个推送用户id
    private Long uid;

    //需要持久化的json
    private PersistenceObjectBean persistence;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public PersistenceObjectBean getPersistence() {
        return persistence;
    }

    public void setPersistence(PersistenceObjectBean persistence) {
        this.persistence = persistence;
    }
}

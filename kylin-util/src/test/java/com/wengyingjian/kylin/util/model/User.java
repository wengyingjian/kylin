package com.wengyingjian.kylin.util.model;

/**
 * Created by wengyingjian on 16/1/29.
 */
public class User {

    private Integer id;
    private String name;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String desc;

}

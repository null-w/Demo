package com.example.zhiming.demo.models;

import java.io.Serializable;

/**
 * Created by zhiming on 2016/4/9.
 */
public class User implements Serializable {
    private String uid;
    private String uname;
    private String upwd;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
}

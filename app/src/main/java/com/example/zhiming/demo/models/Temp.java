package com.example.zhiming.demo.models;

import java.io.Serializable;

/**
 * Created by zhiming on 2016/4/9.
 */
public class Temp implements Serializable {
    private String tid;
    private String tname;


    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}

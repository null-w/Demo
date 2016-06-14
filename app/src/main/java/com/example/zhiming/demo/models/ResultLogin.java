package com.example.zhiming.demo.models;

import java.io.Serializable;

/**
 * Created by zhiming on 2016/6/1.
 */
public class ResultLogin implements Serializable {
    String msg;
    String ctx;
    Boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

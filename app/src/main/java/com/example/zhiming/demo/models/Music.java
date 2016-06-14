package com.example.zhiming.demo.models;

/**
 * Created by zhiming on 2016/5/7.
 */
public class Music {
    private String title;
    private String url;

    public Music(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

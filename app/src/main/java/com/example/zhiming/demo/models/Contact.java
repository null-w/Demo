package com.example.zhiming.demo.models;

import com.example.zhiming.demo.util.pinyin.Pinyin;

/**
 * Created by zhiming on 2016/4/13.
 */
public class Contact {
    private char letter;
    private String name;

    public Contact(String name) {

        this.name = name;
    }

    public char getLetter() {
        letter = Pinyin.toPinyin(getName().charAt(0)).charAt(0);
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.zhiming.demo.util;

import com.example.zhiming.demo.models.Contact;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by zhiming on 2016/4/13.
 */
public class MyComparator implements Comparator<Contact> {


    @Override
    public int compare(Contact lhs, Contact rhs) {
        return String.valueOf(lhs.getLetter()).compareTo(String.valueOf(rhs.getLetter()));
    }
}

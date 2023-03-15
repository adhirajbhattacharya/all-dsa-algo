package com.adhiraj.dsaalgo.leetcode.week5;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Lc981 extends TimeMap{ }

class TimeMap {

    Map<String, TreeMap<Integer, String>> dict = new HashMap<>();

    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> curr = new TreeMap<>();
        curr.put(timestamp, value);
        TreeMap<Integer, String> t = dict.get(key);
        if (t == null) {
            dict.put(key, curr);
            return;
        }

        t.putAll(curr);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> t = dict.get(key);
        if (t == null) return "";
        Map.Entry<Integer, String> e = t.floorEntry(timestamp);
        return e == null ? "" : e.getValue();
    }
}

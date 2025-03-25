package com.adhiraj.dsalgo;

import java.util.HashMap;
import java.util.Map;

// LC-359
public class LoggerRateLimiter {
    Map<String, Integer> messageMap = new HashMap<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer lastTs = messageMap.get(message);
        if (lastTs == null || lastTs + 10 <= timestamp) {
            messageMap.put(message, timestamp);
            return true;
        }
        return false;
    }
}
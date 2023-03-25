package com.adhiraj.dsaalgo.leetcode.week2;

import java.util.HashMap;
import java.util.Map;

public class Lc383 {
    public boolean canConstruct(String ransomNote, String magazine) {

        // can be replaced with array of counts, where index denotes alphabets
        Map<Character, Integer> charCount = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            char key = magazine.charAt(i);
            charCount.merge(key, 1, (oldValue, newValue) -> oldValue + newValue);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char key = ransomNote.charAt(i);
            Integer value = charCount.get(key);
            if (value == null || value == 0) {
                return false;
            } else {
                charCount.put(key, value - 1);
            }
        }

        return true;
    }
}
package com.adhiraj.dsaalgo.leetcode.week2;

import java.util.HashMap;
import java.util.Map;

public class Lc3 {
    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> seen = new HashMap<>();

        int i = 0, j = 0;
        int longest = 0;

        while(i <= j && j < s.length()) {
            char c = s.charAt(j);
            Integer indexOfJ = seen.get(c);
            if (indexOfJ != null) {
                longest = Math.max(longest, j - i);
                while (i <= indexOfJ) {
                    char r = s.charAt(i);
                    seen.remove(r);
                    i++;
                }
            }
            seen.put(c, j);
            j++;
        }
        longest = Math.max(longest, j - i);
        return longest;
    }
}
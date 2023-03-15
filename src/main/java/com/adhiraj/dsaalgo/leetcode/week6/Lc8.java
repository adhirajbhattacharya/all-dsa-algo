package com.adhiraj.dsaalgo.leetcode.week6;

public class Lc8 {
    public int myAtoi(String s) {

        s = s.trim();
        if (s.length() == 0) return 0;
        int sign = 1;
        if (s.charAt(0) == '-') {
            sign = -1;
            s = s.substring(1);
        } else if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        long res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) break;
            res = res * 10 + s.charAt(i) - '0';
            long val = res * sign;
            if (val > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            else if (val < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }

        return (int) res * sign;

    }
}

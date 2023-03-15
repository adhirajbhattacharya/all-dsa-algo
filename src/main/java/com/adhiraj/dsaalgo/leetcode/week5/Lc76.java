package com.adhiraj.dsaalgo.leetcode.week5;

public class Lc76 {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";

        int[] chars = new int[52];

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (Character.isUpperCase(c)) chars[c - 'A' + 26]++;
            else chars[c -'a']++;
        }

        int l = 0, r = 0;
        String minWindow = "";

        while (r < s.length()) {
            char c = s.charAt(r);
            if (Character.isUpperCase(c)) chars[c - 'A' + 26]--;
            else chars[c -'a']--;

            while (allCharsIncluded(chars)) {
                if (minWindow.length() == 0 || minWindow.length() > r - l + 1)
                    minWindow = s.substring(l, r + 1);

                c = s.charAt(l);
                if (Character.isUpperCase(c)) chars[c - 'A' + 26]++;
                else chars[c -'a']++;

                l++;
            }

            r++;
        }
        return minWindow;
    }

    private boolean allCharsIncluded(int[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 0) return false;
        }
        return true;
    }
}
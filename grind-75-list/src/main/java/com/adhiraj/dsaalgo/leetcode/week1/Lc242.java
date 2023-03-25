package com.adhiraj.dsaalgo.leetcode.week1;

public class Lc242 {
    public boolean isAnagram(String s, String t) {
        int[] charCount = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            if (charCount[t.charAt(i) - 'a'] == 0) {
                return false;
            }
            charCount[s.charAt(i) - 'a']--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Lc242().isAnagram("car","rat"));
    }
}

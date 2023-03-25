package com.adhiraj.dsaalgo.leetcode.week3;

// Only needs the length that can be formed
public class Lc409 {
    public int longestPalindrome(String s) {
        int[] alphaCount = new int[52];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                alphaCount[c - 'A' + 26]++;
            } else {
                alphaCount[c - 'a']++;
            }
        }

        int max = 0, odd = 0;

        for (int i = 0; i < 52; i++) {
            if (alphaCount[i] % 2 == 1 && odd == 0)
                odd++;
            max += alphaCount[i] / 2 * 2; // to take out the extra for odd
        }

        return max + odd;
    }
}
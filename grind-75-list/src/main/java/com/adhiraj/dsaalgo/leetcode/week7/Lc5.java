package com.adhiraj.dsaalgo.leetcode.week7;

// Alt solution is easier to understand, same logic
public class Lc5 {
    public String longestPalindrome(String s) {
        String res = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String a = checkPalindrome(s, i);

            if (a.length() > res.length()) res = a;
        }

        return res;
    }

    private String checkPalindrome(String s, int idx) {
        // check for odd length palindrome with idx as middle
        int left = idx - 1, right = idx + 1;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) break;
            left--;
            right++;
        }
        String odd = s.substring(left + 1, right);

        // check for even length palindrome with idx and idx-1 as the middle
        if (s.charAt(idx) != s.charAt(idx - 1)) return odd;

        left = idx - 2;
        right = idx + 1;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) break;
            left--;
            right++;
        }
        String even = s.substring(left + 1, right);

        return even.length() > odd.length() ? even : odd;
    }
}
package com.adhiraj.dsaalgo.leetcode.week1;

public class Lc125 {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            char a = s.charAt(i);
            char b = s.charAt(j);

            if (!Character.isLetterOrDigit(a)) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(b)) {
                j--;
                continue;
            }

            if (Character.toLowerCase(a) != Character.toLowerCase(b))
                return false;
            i++;
            j--;
        }
        return true;
    }
}


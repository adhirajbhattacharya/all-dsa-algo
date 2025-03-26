package com.adhiraj.dsalgo.recursion.recursion.in.programming.full.course;

public class PalindromeString {
    public static void main(String[] args) {
        String[] strArr = {"the simple engineer", "test", "kayak"};
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i] + " :: " + isPalindrome(strArr[i], 0));
        }
    }

    public static boolean isPalindrome(String str, int idx) {
        int otherIdx = str.length() - 1 - idx;
        if (idx > otherIdx) return true;
        if (str.charAt(idx) != str.charAt(otherIdx)) return false;
        return isPalindrome(str, idx + 1);
    }
}

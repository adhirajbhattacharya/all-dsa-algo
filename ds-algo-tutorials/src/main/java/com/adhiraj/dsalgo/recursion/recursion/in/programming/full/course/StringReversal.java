package com.adhiraj.dsalgo.recursion.recursion.in.programming.full.course;

public class StringReversal {
    public static void main(String[] args) {
        String[] strArr = {"the simple engineer", "test", "kayak"};
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i] + " :: " + reverse(strArr[i], 0, ""));
        }
    }

    public static String reverse(String str, int idx, String reverse) {
        if (idx == str.length()) return reverse;
        return reverse(str, idx + 1, str.charAt(idx) + reverse);
    }
}

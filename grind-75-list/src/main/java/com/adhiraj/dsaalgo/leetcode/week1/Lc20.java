package com.adhiraj.dsaalgo.leetcode.week1;

import java.util.ArrayDeque;
import java.util.Deque;

public class Lc20 {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) return false;
            char d = stack.pop();
            if ((d == '(' && c != ')') || (d == '{' && c != '}')
                    || (d == '[' && c != ']'))
                return false;
        }

        return stack.isEmpty();
    }
}

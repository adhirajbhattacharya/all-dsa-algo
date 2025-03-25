package com.adhiraj.dsaalgo.leetcode.week1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 20. Valid Parentheses
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */

/**
 * O(N) TIME & O(N) SPACE
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Set<Character> openers = new HashSet<>();
        openers.add('(');
        openers.add('{');
        openers.add('[');
        int l = s.length();
        if (l % 2 == 1) return false;
        Deque<Character> openersseen = new ArrayDeque<>();
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (openers.contains(c))
                openersseen.push(c);
            else {
                if (openersseen.isEmpty())
                    return false;
                char opener = openersseen.pop();
                if (c == ')' && opener != '(')
                    return false;
                if (c == '}' && opener != '{')
                    return false;
                if (c == ']' && opener != '[')
                    return false;
            }
        }
        return openersseen.isEmpty();
    }
}

package com.adhiraj.dsaalgo.leetcode.week1;

/**
 * 125. Valid Palindrome
 *
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 * Constraints:
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */

/**
 * O(N) TIME & O(1) SPACE
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char l = s.charAt(i);
            char r = s.charAt(j);
            if (!Character.	isLetterOrDigit(l)) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(r)) {
                j--;
                continue;
            }
            if (Character.toLowerCase(l) != Character.toLowerCase(r)) return false;
            i++;
            j--;
        }
        return true;
    }
}


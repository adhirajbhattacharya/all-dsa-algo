package com.adhiraj.dsaalgo.leetcode.week2;

import java.util.Arrays;

/**
 * 383. Ransom Note
 *
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 *
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 *
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 * Constraints:
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 */

/**
 * O(N) TIME & O(1) SPACE
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) return false;

        int[] charCount = new int[26];

        int i = 0;
        while (i < ransomNote.length()) {
            charCount[ransomNote.charAt(i) - 'a'] -= 1;
            charCount[magazine.charAt(i) - 'a'] += 1;
            i++;
        }

        while (i < magazine.length()) {
            charCount[magazine.charAt(i) - 'a'] += 1;
            i++;
        }

        return Arrays.stream(charCount).filter(count -> count < 0).count() == 0;
    }
}
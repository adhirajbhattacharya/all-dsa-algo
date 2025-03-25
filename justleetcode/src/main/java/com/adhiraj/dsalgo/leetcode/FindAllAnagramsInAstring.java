package com.adhiraj.dsalgo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * Constraints:
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */

/**
 * O(N) TIME & O(1) SPACE
 */
public class FindAllAnagramsInAstring {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> idxs = new ArrayList<>();
        if (s.length() < p.length()) return idxs;
        int[] count = new int[26];

        for (int i = 0; i < p.length(); i++) {
            int idx1 = p.charAt(i) - 'a';
            int idx2 = s.charAt(i) - 'a';
            count[idx1] += 1;
            count[idx2] -= 1;
        }

        int i = 0, j = p.length() - 1;
        count[s.charAt(j) - 'a'] += 1;
        while (j < s.length()) {
            count[s.charAt(j) - 'a'] -= 1;
            if (checkAllZero(count)) {
                idxs.add(i);
            }
            count[s.charAt(i) - 'a'] += 1;
            i++;
            j++;
        }
        return idxs;
    }

    private boolean checkAllZero(int[] count) {
        for (int i : count) {
            if (i != 0) return false;
        }
        return true;
    }
}
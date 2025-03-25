package com.adhiraj.dsaalgo.leetcode.week8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *
 * Constraints:
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */
public class FindAnagram {

    public static void main(String[] args) {
        System.out.println(new FindAnagram().findAnagrams("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (p.length() > s.length()) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        int[] countMap = new int[26];
        for (int i = 0; i < p.length(); i++) {
            countMap[s.charAt(i) - 'a'] -= 1;
            countMap[p.charAt(i) - 'a'] += 1;
        }

        // sliding window --> initially brute force came to mind
        int i = 0, j = p.length() - 1;
        while (j + 1 < s.length()) {
            if (checkIfAllValueZero(countMap)) result.add(i);
            countMap[s.charAt(i) - 'a'] += 1;
            i++;
            j++;
            countMap[s.charAt(j) - 'a'] -= 1;
        }
        if (checkIfAllValueZero(countMap)) result.add(i);
        return result;
    }

    private boolean checkIfAllValueZero(int[] countS) {
        return Arrays.stream(countS).filter(v -> v != 0).count() == 0;
    }
}

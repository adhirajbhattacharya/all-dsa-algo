package com.adhiraj.dsaalgo.leetcode.week6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Need to use trie in case s.startsWith(word, idx) is not allowed
public class Lc139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, wordDict, 0, new HashMap<>());
    }

    private boolean wordBreak(String s, List<String> wordDict, int idx, Map<Integer, Boolean> memo) {
        Boolean res = memo.get(idx);
        if (res != null) return res;

        if (idx == s.length()) {
            memo.put(idx, true);
            return true;
        }

        res = false;
        for (String word : wordDict) {
            if (s.startsWith(word, idx)) {
                res = wordBreak(s, wordDict, idx + word.length(), memo);
            }

            if (res) {
                memo.put(idx, res);
                return res;
            }
        }

        memo.put(idx, res);
        return res;
    }
}
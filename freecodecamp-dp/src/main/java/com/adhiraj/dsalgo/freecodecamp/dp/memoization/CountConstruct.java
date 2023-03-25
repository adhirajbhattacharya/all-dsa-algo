package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.HashMap;
import java.util.Map;

public class CountConstruct {

    public static void main(String[] args) {
        System.out.println(solve("abcdef", new String[]{"abc", "cdef", "def", "ab", "cd","ef","abcd"}));
        System.out.println(solve("abcdef", new String[]{"ab", "abc", "abcd", "c", "cd", "ef", "def"}));
        System.out.println(solve("hello", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(solve("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(solve("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee", "f"}));
    }

    public static long solve(String target, String[] words) {
        return solveMemoMap(target, words, new HashMap<>());
//        return solveMemoArray(target, words, new Long[target.length() + 1]);
    }

    private static long solveMemoMap(String target, String[] words, Map<String, Long> memo) {
        if (target.equals("")) return 1;

        Long count = memo.get(target);
        if (count != null) return count;

        count = 0L;
        for (String word : words) {
            if (target.startsWith(word)) {
                long curr = solveMemoMap(target.substring(word.length()), words, memo);
                count += curr;
            }
        }
        memo.put(target, count);
        return count;
    }

    private static long solveMemoArray(String target, String[] words, Long[] memo) {
        if (target.equals("")) return 1;

        Long count = memo[target.length()];
        if (count != null) return count;

        count = 0L;
        for (String word : words) {
            if (target.startsWith(word)) {
                long curr = solveMemoArray(target.substring(word.length()), words, memo);
                count += curr;
            }
        }
        memo[target.length()] = count;
        return count;
    }
}

package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.HashMap;
import java.util.Map;

public class CanConstruct {

    public static void main(String[] args) {
        System.out.println(solve("abc", new String[] {"a", "b", "c", "ab"}));
        System.out.println(solve("abcdef", new String[] {"abc", "cdef", "def", "ab", "cd", "ef", "abcd"}));
        System.out.println(solve("abcdef", new String[] {"ab", "abc", "abcd", "c", "cd", "ef", "def"}));
        System.out.println(solve("hello", new String[] {"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(solve("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(solve("enterapotentpot", new String[] {"a", "p", "ent", "enter", "ot", "o", "t"}));
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee", "f"}));
    }

    public static boolean solve(String target, String[] words) {
//        return solveMemoMap(target, words, new HashMap<>());
        return solveMemoArray(target, words, new Boolean[target.length() + 1]);
    }

    private static boolean solveMemoMap(String target, String[] words, Map<String, Boolean> memo) {
        if (target.isEmpty()) return true;
        Boolean result = memo.get(target);
        if (result != null) return result;
        result = false;
        for (String word : words) {
            if (!target.startsWith(word)) continue;
            String remainder = target.substring(word.length());
            result = Boolean.logicalOr(result, solveMemoMap(remainder, words, memo));
            if (result) break;
        }
        memo.put(target, result);
        return result;
    }

    private static boolean solveMemoArray(String target, String[] words, Boolean[] memo) {
        if (target.isEmpty()) return true;
        Boolean result = memo[target.length()];
        if (result != null) return result;
        result = false;
        for (String word : words) {
            if (!target.startsWith(word)) continue;
            String remainder = target.substring(word.length());
            result = Boolean.logicalOr(result, solveMemoArray(remainder, words, memo));
            if (result) break;
        }
        memo[target.length()] = result;
        return result;
    }
}

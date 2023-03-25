package com.adhiraj.dsalgo.freecodecamp.dp.tabulation;

import java.util.Map;

public class CanConstruct {

    public static void main(String[] args) {
        System.out.println(solve("abcdef", new String[]{"abc", "cdef", "def", "ab", "cd", "ef", "abcd"}));
        System.out.println(solve("abcdef", new String[]{"ab", "abc", "abcd", "c", "cd", "ef", "def"}));
        System.out.println(solve("hello", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(solve("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(solve("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee", "f"}));

    }

    public static boolean solve(String target, String[] words) {
        return solveTab(target, words);
    }

    private static boolean solveTab(String target, String[] words) {
        boolean[] memo = new boolean[target.length() + 1];
        memo[0] = true;

        for (int i = 0; i<memo.length; i++) {
            if (!memo[i])continue;
            for (int j = 0;j<words.length; j++) {
                if (target.substring(i).startsWith(words[j])) {
                    memo[i + words[j].length()] = true;
                }
            }
        }

        return memo[target.length()];
    }

    private static boolean solveMemoMap(String target, String[] words, Map<String, Boolean> memo) {
        if (target.equals("")) return true;

        Boolean val = memo.get(target);
        if (val != null) return val;

        for (String word : words) {
            if (target.startsWith(word)) {
                boolean possible = solveMemoMap(target.substring(word.length()), words, memo);
                if (possible) {
                    memo.put(target, true);
                    return true;
                }
            }
        }

        memo.put(target, false);
        return false;
    }

    private static boolean solveMemoArray(String target, String[] words, Boolean[] memo) {
        if (target.equals("")) return true;

        if (memo[target.length()] != null) return memo[target.length()];

        for (String word : words) {
            if (target.startsWith(word)) {
                boolean possible = solveMemoArray(target.substring(word.length()), words, memo);
                if (possible) {
                    memo[target.length()] = true;
                    return true;
                }
            }
        }

        memo[target.length()] = false;
        return false;
    }
}

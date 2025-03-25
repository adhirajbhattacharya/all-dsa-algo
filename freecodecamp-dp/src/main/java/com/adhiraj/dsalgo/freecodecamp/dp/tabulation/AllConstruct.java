package com.adhiraj.dsalgo.freecodecamp.dp.tabulation;

import java.util.*;
import java.util.stream.Collectors;

public class AllConstruct {

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

    public static List<List<String>> solve(String target, String[] words) {
        return solveTab2(target, words);
    }

    private static List<List<String>> solveTab(String target, String[] words) {
        List<List<String>>[] memo = (List<List<String>>[]) new List[target.length() + 1];
        for (int i = 0; i < memo.length; i++) memo[i] = new ArrayList<>();
        memo[0].add(new ArrayList<>());
        for (int i = 0; i < memo.length; i++) {
            if (memo[i].isEmpty()) continue;
            String currTarget = target.substring(i);
            for (String word : words) {
                if (!currTarget.startsWith(word)) continue;
                List<List<String>> res = memo[i].stream().map(ArrayList::new).peek(list -> list.add(word)).collect(Collectors.toList());
                memo[i + word.length()].addAll(res);
            }
        }
        return memo[target.length()];
    }

    public static List<List<String>> solveTab2(String target, String[] wordBank) {
        List<List<String>>[] table = (List<List<String>>[]) new List[target.length() + 1];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<>();
        }
        table[0].add(new ArrayList<>());

        for (int i = 0; i <= target.length(); i++) {
            for (String word : wordBank) {
                if (i + word.length() <= target.length() && target.substring(i, i + word.length()).equals(word)) {
                    List<List<String>> newCombinations = new ArrayList<>();
                    for (List<String> arr : table[i]) {
                        List<String> newCombination = new ArrayList<>(arr);
                        newCombination.add(word);
                        newCombinations.add(newCombination);
                    }
                    table[i + word.length()].addAll(newCombinations);
                }
            }
        }
        return table[target.length()];
    }


}

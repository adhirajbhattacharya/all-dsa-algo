package com.adhiraj.dsalgo.freecodecamp.dp.tabulation;

import java.util.*;
import java.util.stream.Collectors;

public class AllConstruct {

    public static void main(String[] args) {
        System.out.println(solve("abcdef", new String[]{"abc", "cdef", "def", "ab", "cd", "ef", "abcd"}));
        System.out.println(solve("abcdef", new String[]{"ab", "abc", "abcd", "c", "cd", "ef", "def"}));
        System.out.println(solve("hello", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(solve("skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
        System.out.println(solve("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));
    }

    public static List<List<String>> solve(String target, String[] words) {
        return solveTab(target, words);
    }

    private static List<List<String>> solveTab(String target, String[] words) {
        Set<List<String>>[] memo = new Set[target.length() + 1];

        memo[0] = new HashSet<>();
        memo[0].add(new ArrayList<>());

        for (int i = 0; i < memo.length; i++) {
            if (memo[i] == null) continue;
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                if (target.substring(i).startsWith(word)) {
                    if (memo[i + word.length()] == null) {
                        memo[i + word.length()] = new HashSet<>();
                    }
                    memo[i + word.length()]
                            .addAll(memo[i].stream()
                                    .map(list -> {
                                        List<String> newList = new ArrayList<>(list);
                                        newList.add(word);
                                        newList.sort(String::compareTo);
                                        return newList;
                                    })
                                    .collect(Collectors.toList()));
                }
            }
        }

        return memo[target.length()] == null ? null : new ArrayList<>(memo[target.length()]);
    }

    private static List<List<String>> solveMemoMap(String target, String[] words, Map<String, List<List<String>>> memo) {
        if (target.equals("")) {
            List<List<String>> starter = new ArrayList<>();
            starter.add(new ArrayList<>());
            return starter;
        }

        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        List<List<String>> val = null;

        for (String word : words) {
            if (target.startsWith(word)) {
                List<List<String>> curr = solveMemoMap(target.substring(word.length()), words, memo);
                if (curr != null) {
                    curr = new ArrayList<>(curr);
                    curr = curr.parallelStream().map(list -> {
                        List<String> newList = new ArrayList<>(list);
                        return newList;
                    }).map(list -> {
                        list.add((word));
                        return list;
                    }).collect(Collectors.toList());

                    if (val == null) val = new ArrayList<>();
                    val.addAll(curr);
                }
            }
        }
        memo.put(target, val);
        return val;
    }

    private static List<List<String>> solveMemoArray(String target, String[] words, List<List<String>>[] memo) {
        if (target.equals("")) {
            List<List<String>> starter = new ArrayList<>();
            starter.add(new ArrayList<>());
            return starter;
        }

        if (memo[target.length()] != null) {
            return memo[target.length()];
        }
        List<List<String>> val = null;

        for (String word : words) {
            if (target.startsWith(word)) {
                List<List<String>> curr = solveMemoArray(target.substring(word.length()), words, memo);
                if (curr != null) {
                    curr = new ArrayList<>(curr);
                    curr = curr.parallelStream().map(list -> {
                        List<String> newList = new ArrayList<>(list);
                        return newList;
                    }).map(list -> {
                        list.add((word));
                        return list;
                    }).collect(Collectors.toList());

                    if (val == null) val = new ArrayList<>();
                    val.addAll(curr);
                }
            }
        }
        memo[target.length()] = val;
        return val;
    }
}

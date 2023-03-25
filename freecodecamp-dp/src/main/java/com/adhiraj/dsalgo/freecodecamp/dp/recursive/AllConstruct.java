package com.adhiraj.dsalgo.freecodecamp.dp.recursive;

import java.util.ArrayList;
import java.util.List;

public class AllConstruct {

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

    public static List<List<String>> solve(String target, String[] words) {
        if (target.equals("")) {
            List<List<String>> starter = new ArrayList<>();
            starter.add(new ArrayList<>());
            return starter;
        }

        List<List<String>> val = null;

        for (String word : words) {
            if (target.startsWith(word)) {
                List<List<String>> curr = solve(target.substring(word.length()), words);
                if (curr != null) {
                    curr = new ArrayList<>(curr);
                    curr.parallelStream().forEach(wordList -> wordList.add(word));

                    if (val == null) val = new ArrayList<>();
                    val.addAll(curr);
                }
            }
        }

        return val;
    }
}

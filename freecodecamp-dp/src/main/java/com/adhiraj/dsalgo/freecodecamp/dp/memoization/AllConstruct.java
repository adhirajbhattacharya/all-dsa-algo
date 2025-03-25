package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        System.out.println(solve("eeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee", "eeeee", "eeeeee", "f"}));
    }

    public static List<List<String>> solve(String target, String[] words) {
//        return solveMemoMap(target, words, new HashMap<>());
        return solveMemoArray(target, words, new List[target.length() + 1]);
    }

    private static List<List<String>> solveMemoMap(String target, String[] words, Map<String, List<List<String>>> memo) {
        if (target.isEmpty()) {
            List<List<String>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        List<List<String>> result = memo.get(target);
        if (result != null) return result;
        result = new ArrayList<>(); // for all negative case where no match found send empty outer list instead of null
        for (String word : words) {
            if (!target.startsWith(word)) continue;
            List<List<String>> curr = solveMemoMap(target.substring(word.length()), words, memo);
            if (curr.isEmpty()) continue;
            result.addAll(curr.parallelStream().map(ArrayList::new).peek(list -> list.add(word)).toList());
        }
        memo.put(target, result);
        return result;
    }

    private static List<List<String>> solveMemoArray(String target, String[] words, List<List<String>>[] memo) {
        if (target.isEmpty()) {
            List<List<String>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        List<List<String>> result = memo[target.length()];
        if (result != null) return result;
        result = new ArrayList<>(); // for all negative case where no match found send empty outer list instead of null
        for (String word : words) {
            if (!target.startsWith(word)) continue;
            List<List<String>> curr = solveMemoArray(target.substring(word.length()), words, memo);
            if (curr.isEmpty()) continue;
            result.addAll(curr.parallelStream().map(ArrayList::new).peek(list -> list.add(word)).toList());
        }
        memo[target.length()] = result;
        return result;
    }
}

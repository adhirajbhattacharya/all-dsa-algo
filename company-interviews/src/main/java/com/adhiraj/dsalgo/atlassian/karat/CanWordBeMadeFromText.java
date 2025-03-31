package com.adhiraj.dsalgo.atlassian.karat;

/**
 * Input:
 * words = {"baby", "cat", "referee"}
 * note = "teststringcat"
 *
 * Given:
 * 1. All character will be lowercase alphabets.
 * 2. Only one of the words, if at all, can be made from note.
 * 3. Letters in note can be in any order.
 * 4. Letters in note cannot be reused unless duplicated in note (if your word has 2 't' then note will have to have 2 't').
 * 5. If none found return "_".
 */


public class CanWordBeMadeFromText {

    public static void main(String[] args) {

    }

    public static String findWord(String[] words, String note) {
        int[] noteCount = getAlphabetCount(note, new int[26]);

        for (String word : words) {
            int[] wordCount = getAlphabetCount(word, new int[26]);
            boolean isFound = true;
            if (canWordBeFormed(word, noteCount, wordCount)) return word;
        }

        return "_";
    }

    private static boolean canWordBeFormed(String word, int[] noteCount, int[] wordCount) {
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (noteCount[idx] < wordCount[idx]) return false;
        }
        return true;
    }

    private static int[] getAlphabetCount(String word, int[] countArr) {
        for (int i = 0; i < word.length(); i++) countArr[word.charAt(i) - 'a'] += 1;
        return countArr;
    }
}

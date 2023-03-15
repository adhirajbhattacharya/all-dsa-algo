package com.adhiraj.dsaalgo.leetcode.week7;

import com.adhiraj.dsaalgo.leetcode.GraphWithMap;

import java.util.*;

// below alt solution does not use an actual graph ds but is bfs
public class Lc127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        wordList.add(beginWord);
        GraphWithMap g = new GraphWithMap();
        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (isTranformPossible(wordList.get(i), wordList.get(j)))
                    g.addEdge(wordList.get(i), wordList.get(j));
            }
        }

        return bfs(g, beginWord, endWord);
    }

    private int bfs(GraphWithMap g, String beginWord, String endWord) {
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        q.offer(null);
        int count = 1;
        String curr = null;

        Set<String> visited = new HashSet<>();

        while (!q.isEmpty()) {
            curr = q.poll();
            if (curr == null && q.isEmpty()) break;
            else if (curr == null) {
                count++;
                q.offer(null);
                curr = q.poll();
            }
            if (endWord.equals(curr)) break;
            if (visited.contains(curr)) continue;
            visited.add(curr);

            List<String> nodes = g.adj.get(curr);
            if (nodes == null) continue;

            for (String n : g.adj.get(curr)) {
                q.offer(n);
            }
        }

        return endWord.equals(curr) ? count : 0;
    }

    private boolean isTranformPossible(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length() && diff < 2; i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }

        return diff == 1;
    }
}

class Lc127AltBfsWithoutGraphDs {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        return bfs(wordList, beginWord, endWord);
    }

    private int bfs(List<String> wordList, String beginWord, String endWord) {
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        q.offer(null);
        int count = 1;
        String curr = null;

        while (!q.isEmpty()) {
            curr = q.poll();
            if (curr == null && q.isEmpty()) break;
            else if (curr == null) {
                count++;
                q.offer(null);
                curr = q.poll();
            }

            if (endWord.equals(curr)) break;

            List<String> seen = new ArrayList<>();

            for (String n : wordList) {
                if (isTranformPossible(n, curr)) {
                    seen.add(n);
                    q.offer(n);
                }
            }
            wordList.removeAll(seen);
        }

        return endWord.equals(curr) ? count : 0;
    }

    private boolean isTranformPossible(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length() && diff < 2; i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }

        return diff == 1;
    }
}
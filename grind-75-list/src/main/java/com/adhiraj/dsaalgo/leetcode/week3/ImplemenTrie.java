package com.adhiraj.dsaalgo.leetcode.week3;

import com.adhiraj.dsaalgo.leetcode.TrieNode;
public class ImplemenTrie {

    TrieNode root = new TrieNode();

    public void insert(String word) {
        int n = word.length();
        TrieNode curr = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (curr.next[c - 'a'] == null) curr.next[c - 'a'] = new TrieNode();
            curr = curr.next[c - 'a'];
        }
        curr.end = true;
    }

    public boolean search(String word) {
        TrieNode node = getNodeEndingAt(word);
        return node != null && node.end;
    }

    public boolean startsWith(String prefix) {
        return getNodeEndingAt(prefix) != null;
    }

    private TrieNode getNodeEndingAt(String query) {
        int n = query.length();
        TrieNode curr = root;
        for (int i = 0; i < n && curr != null; i++)
            curr = curr.next[query.charAt(i) - 'a'];
        return curr;
    }
}
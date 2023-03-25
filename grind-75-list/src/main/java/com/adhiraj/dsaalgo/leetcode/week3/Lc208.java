package com.adhiraj.dsaalgo.leetcode.week3;

import com.adhiraj.dsaalgo.leetcode.TrieNode;

// No need to overthink, build as tree with nodes having 26 children
public class Lc208 extends Trie{

}

class Trie {

    TrieNode[] trie = new TrieNode[26];

    public Trie() { }

    public void insert(String word) {
        int n = word.length();

        char c = word.charAt(0);
        if (trie[c - 'a'] == null)
            trie[c - 'a'] = new TrieNode(c);

        TrieNode node = trie[c - 'a'];

        for (int i = 1; i < n; i++) {
            c = word.charAt(i);
            if (node.next[c - 'a'] == null)
                node.next[c - 'a'] = new TrieNode(c);
            node = node.next[c - 'a'];
        }

        node.end = true;
    }

    public boolean search(String word) {
        TrieNode node = getNodeEndingAt(word);

        return node == null ? false : node.end;
    }

    public boolean startsWith(String prefix) {
        return getNodeEndingAt(prefix) != null;
    }

    private TrieNode getNodeEndingAt(String query) {
        int n = query.length();

        char c = query.charAt(0);
        if (trie[c - 'a'] == null)
            return null;

        TrieNode node = trie[c - 'a'];

        for (int i = 1; i < n; i++) {
            c = query.charAt(i);
            if (node.next[c - 'a'] == null)
                return null;
            node = node.next[c - 'a'];
        }

        return node;
    }
}
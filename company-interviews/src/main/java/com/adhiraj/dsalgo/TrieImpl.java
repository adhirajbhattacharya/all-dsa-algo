package com.adhiraj.dsalgo;

import java.util.*;

public class TrieImpl {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        System.out.println(trie.search("apps"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("ad"));
        System.out.println(trie.search("applepie"));
        System.out.println(trie.search("rest"));
        System.out.println(trie.search("jan"));
        System.out.println(trie.search("rent"));
        System.out.println(trie.search("beer"));
        System.out.println(trie.search("jam"));
        // [""],[""],["ad"],["applepie"],["rest"],["jan"],["rent"],["beer"],["jam"]
        System.out.println(trie.startsWith("apps"));
        System.out.println(trie.startsWith("app"));

    }
}

class Trie {
    TrieNode root = new TrieNode();
    public Trie() { }

    public void insert(String word) {
        insert(root, word, 0);
    }

    private void insert(TrieNode node, String word, int idx) {
        char curr = word.charAt(idx);
        int nodeidx = curr - 'a';
        node.child[nodeidx] = new TrieNode();
        node.words.add(word);
        if (word.length() - 1 == idx) node.isEnd = true;
        else insert(node.child[nodeidx], word, idx + 1);
    }

    public boolean search(String word) {
        char start = word.charAt(0);
        int idx = start - 'a';
        return root.words.contains(word);
    }

    public boolean startsWith(String prefix) {
        return startsWith(root, prefix, 0);
    }

    private boolean startsWith(TrieNode node, String prefix, int idx) {
        char curr = prefix.charAt(idx);
        int nodeidx = curr - 'a';
        if (node.child[nodeidx] == null) return false;
        if (prefix.length() - 1 == idx) return true;
        return startsWith(node.child[nodeidx], prefix, idx + 1);
    }
}

class TrieNode {
    TrieNode[] child = new TrieNode[26];
    boolean isEnd = false;
    Set<String> words = new HashSet<>();
}


package com.adhiraj.dsaalgo.leetcode;

public class TrieNode {
    public char c;
    public boolean end = false;
    public TrieNode[] next = new TrieNode[26];

    public TrieNode(char c) {
        this.c = c;
    }
}
package com.adhiraj.dsaalgo.leetcode.week8;

import java.util.HashMap;

class LRUCache {

    HashMap<Integer, DoublyLinkedListNode> cache = new HashMap<>();
    DoublyLinkedListNode lruhead, lrutail;
    int size = 0;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        lruhead = new DoublyLinkedListNode(-1, -1);
        lrutail = lruhead;
    }

    public int get(int key) {
        if (size == 0) return -1;

        DoublyLinkedListNode val = cache.get(key);
        if (val == null) return -1;

        makeMostRecentlyUsed(val);
        return val.value;
    }

    private void makeMostRecentlyUsed(DoublyLinkedListNode node) {
        removeFromLru(node);
        disconnectNode(node);
        offerToLru(node);
    }

    private void removeFromLru(DoublyLinkedListNode node) {
        if (node == lrutail) {
            lrutail = node.prev;
            lrutail.next = null;
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void disconnectNode(DoublyLinkedListNode node) {
        node.prev = null;
        node.next = null;
    }

    private void offerToLru(DoublyLinkedListNode node) {
        lrutail.next = node;
        node.prev = lrutail;
        lrutail = node;
    }

    public void put(int key, int value) {
        DoublyLinkedListNode val = cache.get(key);

        if (val != null) {
            updateCacheAndMakeMostRecentlyUsed(val, value);
            return;
        }

        resize();
        val = new DoublyLinkedListNode(key, value);
        cache.put(key, val);
        offerToLru(val);
        size++;
    }

    private void updateCacheAndMakeMostRecentlyUsed(DoublyLinkedListNode node, int value) {
        node.value = value;
        makeMostRecentlyUsed(node);
    }

    private void resize() {
        if (size == capacity) {
            DoublyLinkedListNode lrunode = lruhead.next;
            cache.remove(lrunode.key);
            removeFromLru(lrunode);
            disconnectNode(lrunode);
            size--;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);

        cache = new LRUCache(1);
        cache.put(2, 1);
        cache.get(2);
    }
}

class DoublyLinkedListNode {
    int key, value;
    DoublyLinkedListNode prev, next;

    DoublyLinkedListNode(int key, int value) {
        this.key = key;
        this.value = value;
        prev = null;
        next = null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
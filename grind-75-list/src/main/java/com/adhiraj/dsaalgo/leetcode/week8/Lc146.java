package com.adhiraj.dsaalgo.leetcode.week8;

import java.util.HashMap;

public class Lc146 {

    HashMap<Integer, DoublyLinkedList> cache = new HashMap<>();
    DoublyLinkedList head = null;
    DoublyLinkedList tail = null;
    int size = 0;
    int capacity;

    public Lc146(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (size == 0) return -1;

        DoublyLinkedList val = cache.get(key);
        if (val == null) return -1;

        if (size == 1 || tail == val) return val.val;

        if (head == val) {
            head = head.next;
            head.prev = null;
            val.next = null;
            tail.next = val;
            val.prev = tail;
            tail = tail.next;
            return val.val;
        }

        val.prev.next = val.next;
        val.next.prev = val.prev;
        tail.next = val;
        val.prev = tail;
        tail = tail.next;
        return val.val;
    }

    public void put(int key, int value) {
        if (size == 0) {
            head = new DoublyLinkedList(key, value);
            tail = head;
            cache.put(key, head);
            size++;
            return;
        }

        if (capacity == 1) {
            cache.remove(head.key);
            head = new DoublyLinkedList(key, value);
            tail = head;
            cache.put(key, head);
        }

        DoublyLinkedList val = cache.get(key);

        if (val == null && size < capacity) {
            val = new DoublyLinkedList(key, value);
            tail.next = val;
            val.prev = tail;
            tail = tail.next;
            cache.put(key, val);
            size++;
            return;
        }

        if (val == null) {
            DoublyLinkedList tmp = head;
            head = head.next;
            head.prev = null;
            tmp.next = null;
            cache.remove(tmp.key);
            val = new DoublyLinkedList(key, value);
            tail.next = val;
            val.prev = tail;
            tail = tail.next;
            cache.put(key, val);
            return;
        }

        val.val = value;

        if (size == 1 || tail == val) {
            return;
        }

        if (head == val) {
            head = head.next;
            head.prev = null;
            val.next = null;
            tail.next = val;
            val.prev = tail;
            tail = tail.next;
            return;
        }

        val.prev.next = val.next;
        val.next.prev = val.prev;
        tail.next = val;
        val.prev = tail;
        tail = tail.next;
        return;
    }
}

class DoublyLinkedList {

    int key;
    int val;
    DoublyLinkedList prev;
    DoublyLinkedList next;

    DoublyLinkedList(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
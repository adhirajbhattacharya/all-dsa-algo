package com.adhiraj.dsaalgo.leetcode.week3;

import java.util.ArrayList;
import java.util.List;

// First solution that came to mind was incorrect, see last. Store min till node in the node.
public class Lc155 {

    private Node head;

    public Lc155() { }

    public void push(int val) {
        int min = val;
        if (head != null && head.min < val) {
            min = head.min;
        }

        head = new Node(val, min, head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}

class Node {
    int val;
    int min;
    Node next;

    Node(int val, int min) {
        this.val = val;
        this.min = min;
        this.next = null;
    }

    Node(int val, int min, Node next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}

// getCurrentMin() will not ensure O(1)
class Lc155Incorrect {

    private List<Integer> array;

    private int min;

    public Lc155Incorrect() {
        array = new ArrayList<>();
        min = Integer.MIN_VALUE;
    }

    public void push(int val) {
        array.add(val);
        if (array.size() == 1 || min > val) {
            min = val;
        }
    }

    public void pop() {
        int e = array.remove(array.size() - 1);
        if (min == e) {
            min = getCurrentMin();
        }
    }

    public int top() {
        return array.get(array.size() - 1);
    }

    public int getMin() {
        return min;
    }

    private int getCurrentMin() {
        return array.stream().mapToInt(i -> i).min().orElseGet(() -> Integer.MIN_VALUE);
    }
}

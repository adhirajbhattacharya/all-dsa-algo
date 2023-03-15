package com.adhiraj.dsaalgo.leetcode.week2;

import java.util.Stack;

public class Lc232 { }

class MyQueue {

    private Stack<Integer> queuer;
    private Stack<Integer> dequeuer;

    public MyQueue() {

        // only queue to this
        queuer = new Stack<>();
        // only dequeue from this
        dequeuer = new Stack<>();
    }

    public void push(int x) {
        queuer.push(x);
    }

    public int pop() {
        if (empty())
            return Integer.MIN_VALUE;
        populateDequeuer();
        return dequeuer.pop();
    }

    public int peek() {
        if (empty())
            return Integer.MIN_VALUE;
        populateDequeuer();
        return dequeuer.peek();
    }

    private void populateDequeuer() {
        if (!isDequeuerEmpty())
            return;

        // only populate dequeuer when it is empty, else order is corrupted
        while (!queuer.empty()) {
            dequeuer.push(queuer.pop());
        }
    }

    private boolean isDequeuerEmpty() {
        return dequeuer.empty();
    }

    public boolean empty() {
        return queuer.empty() && dequeuer.empty();
    }
}
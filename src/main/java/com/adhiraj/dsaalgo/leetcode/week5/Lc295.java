package com.adhiraj.dsaalgo.leetcode.week5;

import java.util.PriorityQueue;
import java.util.Queue;

public class Lc295 extends MedianFinder {
}


class MedianFinder {

    Queue<Integer> maxPq = new PriorityQueue<>((a, b) -> b - a);
    Queue<Integer> minPq = new PriorityQueue<>();

    int size = 0;

    public void addNum(int num) {
        if (maxPq.isEmpty()) {
            maxPq.offer(num);
        } else if (size % 2 == 1) {
            if (maxPq.peek() > num) {
                maxPq.offer(num);
                minPq.offer(maxPq.poll());
            } else {
                minPq.offer(num);
            }
        } else {
            if (maxPq.peek() >= num) {
                maxPq.offer(num);
            } else {
                minPq.offer(num);
                maxPq.offer(minPq.poll());
            }
        }
        size++;
    }

    public double findMedian() {
        if (size % 2 == 0) {
            return (maxPq.peek() + minPq.peek()) / 2.0;
        } else {
            return maxPq.peek();
        }
    }
}
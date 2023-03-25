package com.adhiraj.dsaalgo.leetcode.week2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Lc973 {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, new PointComparator());

        for (int i = 0; i < points.length; i++) {
            heap.offer(points[i]);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.toArray(new int[0][0]);
    }
}

class PointComparator implements Comparator<int[]> {
    public int compare(int[] o1, int[] o2) {
        int dist1 = o1[0] * o1[0] + o1[1] * o1[1], dist2 = o2[0] * o2[0] + o2[1] * o2[1];
        return dist2 - dist1;
    }
}
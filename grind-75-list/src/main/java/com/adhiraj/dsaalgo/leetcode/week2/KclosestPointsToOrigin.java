package com.adhiraj.dsaalgo.leetcode.week2;

import com.adhiraj.dsaalgo.leetcode.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 *
 * Constraints:
 * 1 <= k <= points.length <= 104
 * -104 <= xi, yi <= 104
 */

/**
 * O(N*logK) TIME & O(K) SPACE
 */
public class KclosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair> heap = new PriorityQueue<>(k + 1, new PointComparator().reversed());

        for (int i = 0; i < points.length; i++) {
            heap.offer(new Pair(points[i][0], points[i][1]));

            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.stream().map(pair -> new int[] {pair.first, pair.second}).toArray(int[][]::new);
    }
}

class PointComparator implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        int dist1 = p1.first * p1.first + p1.second * p1.second;
        int dist2 = p2.first * p2.first + p2.second * p2.second;
        return Integer.compare(dist1, dist2);
    }
}
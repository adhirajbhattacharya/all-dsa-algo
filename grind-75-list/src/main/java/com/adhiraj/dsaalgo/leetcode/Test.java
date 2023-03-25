package com.adhiraj.dsaalgo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public int[][] insert(int[][] intervals, int[] in) {
        if (intervals.length == 0) return new int[][] {in};

        List<int[]> inter = Arrays.stream(intervals).collect(Collectors.toList());
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < inter.size(); i++) {
            int[] curr = inter.get(i);
            if (in[1] < curr[0]) {
                result.add(in);
                in = curr;
                System.out.println(in[0] + "-" + in[1] + "||" + curr[0] + "-" + curr[1] + "--1");
            } else if (in[0] > curr[1]) {
                result.add(curr);
                System.out.println(in[0] + "-" + in[1] + "||" + curr[0] + "-" + curr[1] + "--2");
            } else {
                in[0] = Math.min(in[0], curr[0]);
                in[1] = Math.max(in[1], curr[1]);
                System.out.println(in[0] + "-" + in[1] + "||" + curr[0] + "-" + curr[1] + "--3");
            }
        }

        result.add(in);

        return result.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        System.out.println(new Test().insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}));
    }
}
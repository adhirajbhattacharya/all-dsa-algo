package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

import java.util.*;

public class LeastNumberOfMeetingRooms {

    public static void main(String[] args) {
        List<List<Integer>> meets = new ArrayList<>();

        List<Integer> meet = new ArrayList<>();
        meet.add(1);
        meet.add(18);
        meets.add(meet);

        meet = new ArrayList<>();
        meet.add(18);
        meet.add(23);
        meets.add(meet);

        meet = new ArrayList<>();
        meet.add(15);
        meet.add(29);
        meets.add(meet);

        meet = new ArrayList<>();
        meet.add(4);
        meet.add(15);
        meets.add(meet);

        meet = new ArrayList<>();
        meet.add(2);
        meet.add(11);
        meets.add(meet);

        meet = new ArrayList<>();
        meet.add(5);
        meet.add(13);
        meets.add(meet);

        System.out.println(solve(meets));
    }

    // from video, mine below
    public static int solve(List<List<Integer>> meets) {
        int n = meets.size();
        Map<Integer, Integer> meetTimes = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> meet = meets.get(i);
            meetTimes.put(meet.get(0), 1);
            meetTimes.put(meet.get(1), -1);
        }

        int count = 0;
        int max = 0;

        for (Map.Entry<Integer, Integer> entry : meetTimes.entrySet()) {
            count += entry.getValue();
            if (count > max) max = count;
        }
        return max;
    }

    // Mine
    public static int solve2(List<List<Integer>> meets) {
        int n = meets.size();
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            List<Integer> meet = meets.get(i);
            starts[i] = meet.get(0);
            ends[i] = meet.get(1);
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int i = 0, j = 0;
        int count = 0;
        int max = 0;

        while (i < n) {
            if (starts[i] < ends[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            if (count > max) max = count;
        }
        return max;
    }
}

package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AssignMiceToHoles {
    public static void main(String[] args) {

    }

    public int solve(List<Integer> mice, List<Integer> holes) {
        Collections.sort(mice);
        Collections.sort(holes);
        int ans = 0;

        for (int i = 0; i < mice.size(); i++) {
            ans = Math.max(ans, Math.abs(mice.get(i) - holes.get(i)));
        }
        return ans;
    }
}

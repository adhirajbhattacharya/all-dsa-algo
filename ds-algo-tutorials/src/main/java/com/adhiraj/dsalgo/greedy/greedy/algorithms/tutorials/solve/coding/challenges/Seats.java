package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

import java.util.ArrayList;
import java.util.List;

public class Seats {
    public static void main(String[] args) {
        System.out.println(solve("..xx.x"));
    }

    public static int solve(String seats) {
        List<Integer> seatedIdxArr = new ArrayList<>();

        for(int i = 0; i < seats.length(); i++) {
            char s =seats.charAt(i);
            if (s == 'x') seatedIdxArr.add(i);
        }

        if (seatedIdxArr.size() < 2) return 0;

        int medianIdx = (seatedIdxArr.size() + 1) / 2 - 1;

        int movements = 0;

        for (int i = medianIdx - 1, toPos = seatedIdxArr.get(medianIdx) - 1; i >= 0; i--, toPos--) {
            movements += (toPos - seatedIdxArr.get(i));
            movements %= 10000003;
        }

        for (int i = medianIdx + 1, toPos = seatedIdxArr.get(medianIdx) + 1; i <seatedIdxArr.size(); i++, toPos++) {
            movements += (seatedIdxArr.get(i) - toPos);
            movements %= 10000003;
        }

        return movements;
    }
}

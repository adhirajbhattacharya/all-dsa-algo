package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

public class BulbSwitchOnAll {

    public static void main(String[] args) {

    }

    public static int bulbSwitchOnAll(int[] bulbs) {
        int cost = 0;
        for (int bulb : bulbs) {
            if (cost % 2 == 0 && bulb == 0) cost++;
            if (cost % 2 == 1 && bulb == 1) cost++;
        }
        return cost;
    }
}

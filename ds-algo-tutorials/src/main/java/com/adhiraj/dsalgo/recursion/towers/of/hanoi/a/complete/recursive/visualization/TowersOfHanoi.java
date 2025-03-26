package com.adhiraj.dsalgo.recursion.towers.of.hanoi.a.complete.recursive.visualization;

public class TowersOfHanoi {
    public static void main(String[] args) {
        towersOfHanoi(3, 1, 3);
    }

    public static void towersOfHanoi(int noOfDiscs, int startRod, int endRod) {
        if (noOfDiscs == 1) {
            move(startRod, endRod);
            return;
        }

        int otherRod = 6 - startRod - endRod;
        towersOfHanoi(noOfDiscs - 1, startRod, otherRod);
        move(startRod, endRod);
        towersOfHanoi(noOfDiscs - 1, otherRod, endRod);
    }

    private static void move(int startRod, int endRod) {
        System.out.println(startRod + " -> " + endRod);
    }
}

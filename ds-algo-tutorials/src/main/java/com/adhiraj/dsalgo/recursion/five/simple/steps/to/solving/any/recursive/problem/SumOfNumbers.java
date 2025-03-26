package com.adhiraj.dsalgo.recursion.five.simple.steps.to.solving.any.recursive.problem;

public class SumOfNumbers {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + " :: " + sumOfNumbers(i));
        }
    }

    public static long sumOfNumbers(int n) {
        if (n == 0) return 0;
        return (long) n + sumOfNumbers(n - 1);
    }
}

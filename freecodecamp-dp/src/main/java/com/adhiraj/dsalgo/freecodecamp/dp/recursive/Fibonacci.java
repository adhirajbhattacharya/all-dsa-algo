package com.adhiraj.dsalgo.freecodecamp.dp.recursive;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(solve(0));
        System.out.println(solve(1));
        System.out.println(solve(2));
        System.out.println(solve(3));
        System.out.println(solve(4));
        System.out.println(solve(5));
        System.out.println(solve(6));
        System.out.println(solve(7));
        System.out.println(solve(8));
        System.out.println(solve(9));
        System.out.println(solve(50));
    }

    public static long solve(long n) {
        if (n < 0) throw new IllegalArgumentException("n:" + n);
        if (n == 0) return 0;
        if (n <= 2) return 1;
        return solve(n - 1) + solve(n - 2);
    }
}

package com.adhiraj.dsalgo.recursion.five.simple.steps.to.solving.any.recursive.problem;

import java.math.BigInteger;
import java.util.HashMap;

public class NumberOfWaysToPartition {
    public static void main(String[] args) {
        System.out.println(9 + " x " + 5 + " :: " + numberOfWaysToPartitionNxM(9, 5));
        System.out.println(1000 + " x " + 1000 + " :: " + numberOfWaysToPartitionNxM(1000, 1000));
    }

    public static BigInteger numberOfWaysToPartitionNxM(int noOfObjects, int maxPartitionSize) {
        return numberOfWaysToPartitionNxM(noOfObjects, maxPartitionSize, new HashMap<>());
    }

    private static BigInteger numberOfWaysToPartitionNxM(int n, int m, HashMap<String, BigInteger> cache) {
        String key = "$" + n + "!" + m;
        BigInteger value = cache.get(key);
        if (value != null) return value;

        if (n == 0) {
            cache.put(key, new BigInteger("1"));
            return cache.get(key);
        }
        if (m == 0 || n < 0) {
            cache.put(key, new BigInteger("0"));
            return cache.get(key);
        }

        cache.put(key, numberOfWaysToPartitionNxM(n - m, m, cache).add(numberOfWaysToPartitionNxM(n, m - 1, cache)));
        return cache.get(key);
    }
}

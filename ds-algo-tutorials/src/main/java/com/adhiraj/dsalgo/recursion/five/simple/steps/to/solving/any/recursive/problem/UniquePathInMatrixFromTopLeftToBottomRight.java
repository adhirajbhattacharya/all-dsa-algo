package com.adhiraj.dsalgo.recursion.five.simple.steps.to.solving.any.recursive.problem;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class UniquePathInMatrixFromTopLeftToBottomRight {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.println(i + " x " + j + " :: " + getUniquePaths(i, j));
            }
        }

        System.out.println(1000 + " x " + 1000 + " :: " + getUniquePaths(1000, 1000));
    }

    public static BigInteger getUniquePaths(int rowSize, int colSize) {
        Map<String, BigInteger> cache = new HashMap<>();
        return getUniquePaths(0, 0, rowSize, colSize, cache);
    }

    private static BigInteger getUniquePaths(int currRow, int currCol, int rowSize, int colSize, Map<String, BigInteger> cache) {
        String key = "$" + currRow + "!" + currCol;
        BigInteger value = cache.get(key);
        if (value != null) return value;

//        if (currRow == rowSize || currCol == colSize) {
//            cache.put(key, new BigInteger("0"));
//            return cache.get(key);
//        }
//
//        if (currRow == rowSize - 1 && currCol == colSize - 1) {
//            cache.put(key, new BigInteger("1"));
//            return cache.get(key);
//        }
//  Commented code also works

        if (currRow == rowSize - 1 || currCol == colSize - 1) {
            cache.put(key, new BigInteger("1"));
            return cache.get(key);
        }

        value = getUniquePaths(currRow + 1, currCol, rowSize, colSize, cache)
                .add(getUniquePaths(currRow, currCol + 1, rowSize, colSize, cache));
        cache.put(key, value);
        return cache.get(key);
    }
}

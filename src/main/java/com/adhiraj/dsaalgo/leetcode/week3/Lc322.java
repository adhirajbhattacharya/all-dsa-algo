package com.adhiraj.dsaalgo.leetcode.week3;

import com.adhiraj.dsaalgo.leetcode.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Most intuitive is to use recursion with branching out for each coins - O(n^m), m = ammount, n = coins
// Add memoization - 0(m*n) time, O(m) space for Map and recursion each
// HAS 3 ALTERNATE SOLUTION (2 IS TABULATION)
public class Lc322 {

    int impossible;
    public int coinChange(int[] coins, int amount) {
        impossible = amount + 1;

        int res = coinChange(coins, amount, new HashMap<>());

        return res >= impossible ? - 1 : res;
    }

    private int coinChange(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0) return 0;

        if (amount < 0) return impossible;

        Integer res = memo.get(amount);

        if (res != null) return res;

        res = impossible;

        for (int i = 0; i < coins.length; i++) {
            res = Math.min(res, 1 + coinChange(coins, amount - coins[i], memo));
        }

        memo.put(amount, res);
        return res;
    }
}

// This uses a use or not use coin at each step. Not required here as coins are unlimited and can be selected more than once.
// Slower by 5x than original solution. How will I get the time complexity for this?
class Lc322AltMemo {
    int impossible;

    public int coinChange(int[] coins, int amount) {
        impossible = amount + 1;

        int res = coinChange(coins, amount, coins.length - 1, new HashMap<>());

        return res >= impossible ? - 1 : res;
    }

    private int coinChange(int[] coins, int amount, int largestIndex,  Map<Pair, Integer> memo) {
        if (amount < 0) return impossible;

        if (amount == 0) return 0;

        if (largestIndex < 0) return impossible;

        Pair key = new Pair(amount, largestIndex);
        Integer res = memo.get(key);

        if (res == null) {
            res = Math.min(1 + coinChange(coins, amount - coins[largestIndex], largestIndex, memo), coinChange(coins, amount, largestIndex - 1, memo));
            memo.put(key, res);
        }

        return res;
    }
}

// This is the 2D tabulation - 0(m*n) time, O(m*n) space
// Below is one with space improved
// Even though same complexity, both tabulation methods are 10x faster than memo
class Lc322AltTabulation {
    public int coinChange(int[] coins, int amount) {
        int[][] tab = new int[coins.length + 1][amount + 1];

        int infinity = amount + 1;

        for (int i = 0; i < tab.length; i++) {
            Arrays.fill(tab[i], infinity);
        }

        for (int i = 0; i < tab.length; i++) {
            tab[i][0] = 0;
        }

        for (int i = 1; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == -1) continue;
                if (coins[i - 1] < tab[0].length - j)
                    tab[i][j + coins[i - 1]] = Math.min(tab[i][j + coins[i - 1]], 1 + tab[i][j]);
                if (i + 1 < tab.length)
                    tab[i + 1][j] = Math.min(tab[i + 1][j], tab[i][j]);
            }
        }

        return tab[coins.length][amount] >= infinity ? -1 : tab[coins.length][amount];
    }
}

// This is improvement on above tabulation, by decreasing space. - 0(m*n) time, O(m) space
class Lc322AltTabulationImproved {
    public int coinChange(int[] coins, int amount) {

        int[] tab = new int[amount + 1];
        Arrays.fill(tab, -1);
        tab[0] = 0;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == -1) continue;
            for (int coin : coins) {
                if (coin < tab.length - i && (tab[i + coin] == -1 || tab[i + coin] > 1 + tab[i])) {
                    tab[i + coin] = 1 + tab[i];
                }
            }
        }

        return tab[amount];
    }
}
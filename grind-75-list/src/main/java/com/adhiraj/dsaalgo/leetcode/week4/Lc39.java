package com.adhiraj.dsaalgo.leetcode.week4;

import java.util.*;

// Initial intuitive is recursion with memoization. But since return is not primitive, need to take care for storing and returning.
// See the comments in below method -- O(m*n*n) where n. Too much sorting and list creation here.

// Always pass result as part of the recursion arguments incase of non primitive return like this.

// The intuition is correct but slow, here memoization is not strictly required. Since we have to traverse all possible paths.
// Check below 2 solutions. Then dont require memoization.

// HAS 2 ALTERNATE SOLUTION
public class Lc39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum(candidates, target, new HashMap<>());
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target, Map<Integer, List<List<Integer>>> memo) {
        Set<List<Integer>> res = new HashSet<>();

        if (target == 0) {
            res.add(new ArrayList<>());
            return new ArrayList(res);
        }

        if (target < 0)
            return new ArrayList(res);

        if (memo.containsKey(target)) {
            List<List<Integer>> tmp = memo.get(target);
            for (List<Integer> j : tmp) {       // have to create new list everytime of return
                res.add(new ArrayList<>(j));
            }
            return new ArrayList(res);
        }

        for (int i : candidates) {
            List<List<Integer>> tmp = combinationSum(candidates, target - i, memo);
            for (List<Integer> j : tmp) {
                j.add(i);                       // have to create new list everytime of return
                Collections.sort(j);
                res.add(j);
            }
        }

        List<List<Integer>> tmp = new ArrayList<>();
        for (List<Integer> j : res) {
            tmp.add(new ArrayList<>(j));        // have to create new list everytime of return
        }
        memo.put(target, tmp);
        return tmp;

        // below can also be used.
//        for (int i : candidates) {
//            List<List<Integer>> tmp = combinationSum(candidates, target - i, memo);
//            tmp = tmp.stream().map(j -> new ArrayList<>(j)).map(j -> {
//                j.add(i);
//                Collections.sort(j);
//                return j;
//            }).collect(Collectors.toList());
//            res.addAll(tmp);
//        }
//
//
//        memo.put(target, new ArrayList(res));
//        return new ArrayList(res);
    }
}

// Here also pass result to be updated on the fly. Initial intuition was also incorrect here. Check wrong intuition method.
class Lc39Alt {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    public void combinationSum(int[] candidates, int target, int idx, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        if (target < 0 || idx == candidates.length)
            return;

        int candidate = candidates[idx];

        curr.add(candidate);
        combinationSum(candidates, target - candidate, idx, curr, res);
        curr.remove(curr.size() - 1);

        combinationSum(candidates, target, idx + 1, curr, res);
    }

    // wrong intuition
    public List<List<Integer>> combinationSum(int[] candidates, int target, int idx) {
        if (target == 0) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());
            return res;
        }

        if (target < 0 || idx == candidates.length)
            return new ArrayList<>();

        int candidate = candidates[idx];

        List<List<Integer>> res = combinationSum(candidates, target - candidate, idx);

        for (List<Integer> list : res) {
            list.add(candidate);
        }

        res.addAll(combinationSum(candidates, target, idx + 1));

        return res;
    }
}

// This is the best in terms of time with alternate
// Since it has issue of copying and pasting pass the result to be populated on the fly.
class Lc39AltBest {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void combinationSum(int[] candidates, int target, int start, List<Integer> curr, List<List<Integer>> res) {

        if (target == 0) {
            res.add(new ArrayList<>(curr));
        }

        if (target < 0) return;

        for (int i = start; i < candidates.length; i++) {
            curr.add(candidates[i]);
            combinationSum(candidates, target - candidates[i], i, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}

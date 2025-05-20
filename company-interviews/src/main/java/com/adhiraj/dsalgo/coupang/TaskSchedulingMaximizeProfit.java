package com.adhiraj.dsalgo.coupang;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 Schedule Optimization with Extension

 You're managing a project schedule. You have a list of tasks, each with a start time, end time, and a value (profit) you'll get if you complete it.

 tasks = [(start_time, end_time, value, penalty, extension_time), ...]

 You can only work on non-overlapping tasks. You want to maximize your total profit.

 Here's the twist: You can pay a penalty for each task to extend its deadline by extension_time. Extending a deadline means the task now ends at end_time + extension_time.

 Your goal: Write a function that takes the list of tasks and returns the maximum total value you can achieve by scheduling non-overlapping tasks, considering the option to extend deadlines for a cost.

 Example:
 1. tasks = [
 (1, 3, 5, 1, 2),  // start, end, value, penalty, extension_time
 (2, 3, 6, 0, 0),
 (3, 5, 2, 2, 1),
 (6, 7, 4, 1, 0)
 ]

 2. tasks = [
 (1, 3, 5, 1, 10),  // start, end, value, penalty, extension_time
 (2, 3, 6, 0, 0),
 (3, 5, 2, 2, 1),
 (6, 7, 4, 1, 0)
 ]

 Breakdown of a task:
 (start_time, end_time, value, penalty, extension_time)
 start_time: When the task starts.
 end_time: When the task initially ends.
 value: The Profit you get if you complete the task.
 penalty: Cost to extend the deadline.
 extension_time: How much time is the deadline extended?

 **/
public class TaskSchedulingMaximizeProfit {

    public static int maximumProfit(List<List<Integer>> tasks) {
//         return maximumProfit(tasks, tasks.size(), 0, new ArrayList<>()); // Initial only O(3^n)

        List<Task> allTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            List<Integer> task = tasks.get(i);
            Task t1 = new Task(i, task.get(0), task.get(1), task.get(2));
            Task t2 = new Task(i, task.get(0) + task.get(4), task.get(1) + task.get(4), task.get(2) - task.get(3));
            allTasks.add(t1);
            allTasks.add(t2);
        }
        int n = allTasks.size();
        Set<Integer>[] tasksPickedMemo = new Set[n];
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            tasksPickedMemo[i] = new HashSet<>();
        }
        allTasks.sort(Comparator.comparingInt(t -> t.end));
        return maximize(allTasks, n - 1, tasksPickedMemo, dp);
    }

    private static int maximize(List<Task> allTasks, int idx, Set<Integer>[] pickedTasksMemo, int[] dp) {
        if (idx < 0) return 0;

        if (dp[idx] != Integer.MIN_VALUE) return dp[idx];

        int profit = maximize(allTasks, idx - 1, pickedTasksMemo, dp);
        if (idx > 0) pickedTasksMemo[idx].addAll(pickedTasksMemo[idx - 1]);

        Task task = allTasks.get(idx);
        int previousNonOverlappingTask = binSearch(allTasks, idx - 1, task.start);

        if (previousNonOverlappingTask != -1) {
            int profitPreviousNonOverlappingTask = maximize(allTasks, previousNonOverlappingTask, pickedTasksMemo, dp);
            if (!pickedTasksMemo[previousNonOverlappingTask].contains(task.id)
                    && profitPreviousNonOverlappingTask + task.profit > profit) {
                profit = profitPreviousNonOverlappingTask + task.profit;
                pickedTasksMemo[idx].clear();
                pickedTasksMemo[idx].addAll(pickedTasksMemo[previousNonOverlappingTask]);
                pickedTasksMemo[idx].add(task.id);
            }
        } else if (profit < task.profit){
            profit = task.profit;
            pickedTasksMemo[idx].clear();
            pickedTasksMemo[idx].add(task.id);
        }

        dp[idx] = profit;
        return profit;
    }

    private static int binSearch(List<Task> allTasks, int hi_1, int start) {
        int lo = 0, hi = hi_1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            Task midTask = allTasks.get(mid);
            if (start >= midTask.end) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    private static int maximumProfit(List<List<Integer>> tasks, int n, int idx,
                                     List<List<Integer>> bookedSlots) {
        if (idx == n) return 0;
        List<Integer> task = tasks.get(idx);

        if (task.get(2) == 0) {
            return maximumProfit(tasks, n, idx + 1, bookedSlots);
        }

        List<Integer> extendedTask = null;

        if (task.get(4) != 0) {
            extendedTask = new ArrayList<>(task);
            extendedTask.set(0, extendedTask.get(0) + extendedTask.get(4));
            extendedTask.set(1, extendedTask.get(1) + extendedTask.get(4));
            extendedTask.set(2, extendedTask.get(2) - extendedTask.get(3));
        }

        int start = task.get(0);
        int end = task.get(1);

        int profit = 0;

        if (isSlotFree(bookedSlots, start, end)) {
            List<Integer> slot = new ArrayList<>();
            slot.add(start);
            slot.add(end);
            bookedSlots.add(slot);
            profit = task.get(2) + maximumProfit(tasks, n, idx + 1, bookedSlots);
            bookedSlots.remove(slot);
        }

        if (extendedTask != null) {
            start = extendedTask.get(0);
            end = extendedTask.get(1);

            int extendedProfit = 0;

            if (isSlotFree(bookedSlots, start, end)) {
                List<Integer> slot = new ArrayList<>();
                slot.add(start);
                slot.add(end);
                bookedSlots.add(slot);
                extendedProfit = extendedTask.get(2) + maximumProfit(tasks, n, idx + 1, bookedSlots);
                bookedSlots.remove(slot);
            }

            profit = Math.max(profit, extendedProfit);
        }

        return Math.max(profit, maximumProfit(tasks, n, idx + 1, bookedSlots));

    }

    private static boolean isSlotFree(List<List<Integer>> bookedSlots, int start, int end) {
        boolean isFree = true;
        for (List<Integer> slot : bookedSlots) {
            int slotStart = slot.get(0);
            int slotEnd = slot.get(1);
            if ((start >= slotStart && start < slotEnd)
                    || (end > slotStart && end <= slotEnd)) {
                isFree = false;
                break;
            }
        }
        return isFree;
    }

    public static void main(String[] args) {
        List<List<Integer>> tasks = new ArrayList<>();

        List<Integer> task = new ArrayList<>();
        task.add(1);
        task.add(3);
        task.add(5);
        task.add(1);
        task.add(2);
        tasks.add(task);

        task = new ArrayList<>();
        task.add(6);
        task.add(7);
        task.add(4);
        task.add(1);
        task.add(0);
        tasks.add(task);

        task = new ArrayList<>();
        task.add(2);
        task.add(3);
        task.add(6);
        task.add(0);
        task.add(0);
        tasks.add(task);

        task = new ArrayList<>();
        task.add(3);
        task.add(5);
        task.add(2);
        task.add(2);
        task.add(1);
        tasks.add(task);

        System.out.print(maximumProfit(tasks));
    }
}

@AllArgsConstructor
class Task {
    int id, start, end, profit;
}

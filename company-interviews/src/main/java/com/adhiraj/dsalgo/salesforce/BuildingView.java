package com.adhiraj.dsalgo.salesforce;

import java.util.*;

/**
 * Question: You are given a list of integers representing height of buildings
 * in a row in the order they exist. A building is able to see another building
 * on its side if all the building in between itself and the other is of lower
 * height than both. Return a list which where each number is the number of
 * buildings it can see on both sides.
 */
public class BuildingView {

    public static void main(String[] args) {
        List<int[]> inputs = new ArrayList<>();
        inputs.add(new int[] {5, 2, 10, 1});
        inputs.add(new int[] {3, 6, 2, 4, 5});
        inputs.add(new int[] {3, 6, 2, 8, 7});
        inputs.stream()
                .peek(arr -> {
                    if (!Arrays.equals(totalViewBruteForce(arr), totalViewMonoStack(arr)))
                        throw new RuntimeException("Brute force and optimized solutions do not match for " + Arrays.toString(arr));
                })
                .map(BuildingView::totalViewMonoStack).map(Arrays::toString).forEach(System.out::println);
    }

    public static int[] totalViewMonoStackSinglePass(int[] height) {
        int n = height.length;
        int[] view = new int[n];

        // Two stacks to track left and right visibility
        Deque<Integer> leftStack = new ArrayDeque<>();
        Deque<Integer> rightStack = new ArrayDeque<>();

        // {3, 6, 2, 8, 7}
        // Traverse the buildings from left to right and right to left in the same loop
        for (int i = 0; i < n; i++) {
            // Left visibility (moving left to right)
            while (!leftStack.isEmpty() && height[leftStack.peek()] < height[i]) {
                view[i]++; // Count the visible building
                leftStack.pop(); // Remove the building since it's not visible anymore
            }
            view[i] += leftStack.size(); // Count all remaining buildings in the left stack
            leftStack.push(i); // Push current building index onto the left stack

            // Right visibility (moving right to left)
            int rightIndex = n - 1 - i; // Calculate the index when moving from right to left
            while (!rightStack.isEmpty() && height[rightStack.peek()] < height[rightIndex]) {
                view[rightIndex]++; // Count the visible building
                rightStack.pop(); // Remove the building since it's not visible anymore
            }
            view[rightIndex] += rightStack.size(); // Count all remaining buildings in the right stack
            rightStack.push(rightIndex); // Push current building index onto the right stack
        }

        return view;
    }

    public static int[] totalViewMonoStack(int[] height) {
        int n = height.length;
        int[] view = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        // {3, 6, 2, 8, 7}
        // Calculate left visibility
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                view[i]++; // Count the visible building
                stack.pop(); // Remove the building since it's not visible anymore
            }
            view[i] += stack.size(); // Count all remaining buildings in the stack
            stack.push(i); // Push current building index onto the stack
        }

        stack.clear();

        // Calculate right visibility
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                view[i]++; // Count the visible building
                stack.pop(); // Remove the building since it's not visible anymore
            }
            view[i] += stack.size(); // Count all remaining buildings in the stack
            stack.push(i); // Push current building index onto the stack
        }

        return view;
    }

    public static int[] totalViewBruteForce(int[] height) {
        int n = height.length;
        int[] view = new int[n];


        for (int i = 0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                if (height[j] > max) {
                    view[i] = view[i] + 1;
                    max = Math.max(max, height[j]);
                }
            }
        }


        for (int i = n - 1; i >= 0; i--) {
            int max = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max) {
                    view[i]++;
                    max = Math.max(max, height[j]);
                }
            }
        }

        return view;
    }
}

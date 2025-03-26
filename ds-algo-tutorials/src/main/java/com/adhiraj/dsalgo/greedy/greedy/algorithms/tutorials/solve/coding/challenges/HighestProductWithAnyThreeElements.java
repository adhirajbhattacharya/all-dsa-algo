package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

import java.util.Arrays;

public class HighestProductWithAnyThreeElements {
    public static void main(String[] args) {

    }

    public static int highestProductWithAnyThreeElements(int[] nums) {
//        Arrays.sort(nums);
//        int lastIdx = nums.length - 1;
//        int productOfFirstTwoAndLast = nums[0] * nums[1] * nums[lastIdx];
//        int productOfLastThree = nums[lastIdx - 2] * nums[lastIdx - 1] * nums[lastIdx];
//        return productOfFirstTwoAndLast > productOfLastThree ? productOfFirstTwoAndLast : productOfLastThree;

        int[] elementsToConsider = new int[5];
        elementsToConsider[0] = Integer.MAX_VALUE;
        elementsToConsider[1] = Integer.MAX_VALUE;
        elementsToConsider[2] = Integer.MIN_VALUE;
        elementsToConsider[3] = Integer.MIN_VALUE;
        elementsToConsider[4] = Integer.MIN_VALUE;

        for (int element : nums) {
            if (element < elementsToConsider[0]) {
                elementsToConsider[1] = elementsToConsider[0];
                elementsToConsider[0] = element;
            }
            else if (element < elementsToConsider[1]) {
                elementsToConsider[1] = element;
            }

            if (element > elementsToConsider[4]) {
                elementsToConsider[2] = elementsToConsider[3];
                elementsToConsider[3] = elementsToConsider[4];
                elementsToConsider[4] = element;
            } else if (element > elementsToConsider[3]) {
                elementsToConsider[2] = elementsToConsider[3];
                elementsToConsider[3] = element;
            } else if (element > elementsToConsider[2]) {
                elementsToConsider[2] = element;
            }
        }
        int productOfFirstTwoAndLast = elementsToConsider[0] * elementsToConsider[1] * elementsToConsider[4];
        int productOfLastThree = elementsToConsider[2] * elementsToConsider[3] * elementsToConsider[4];

        return productOfFirstTwoAndLast > productOfLastThree ? productOfFirstTwoAndLast : productOfLastThree;
    }
}

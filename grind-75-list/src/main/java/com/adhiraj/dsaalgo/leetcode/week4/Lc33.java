package com.adhiraj.dsaalgo.leetcode.week4;

// Find the pivot for rotation and binary search on applicable partition
// Check get pivot logic. Looks simple but is tricky.
public class Lc33 {
    public int search(int[] nums, int target) {
        int pivot = getPivotInRotatedSortedArray(nums);

        int result = -1;
        switch (pivot) {
            case 0:
                result = binarySearch(nums, 0, nums.length - 1, target);
                break;
            default:
                if (target >= nums[0])
                    result = binarySearch(nums, 0, pivot - 1, target);
                else
                    result = binarySearch(nums, pivot, nums.length - 1, target);
        }
        return result;
    }

    private int getPivotInRotatedSortedArray(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) return 0; // no rotation

        // if code comes here there is guaranteed rotation

        int pivot = 0;
        int lo = 0, hi = nums.length - 1, mid;

        while (lo <= hi) {
            mid = (lo + hi) / 2;

            if (mid < nums.length - 1 && nums[mid + 1] < nums[mid]) { // if mid is nums.length then below else will be called
                pivot = mid + 1;                                    // else we will check for pivot at mid + 1
                break;
            }

            if (nums[mid] >= nums[lo]) lo = mid + 1; // means nums[lo] to nums[mid] is sorted already and pivot is after mid
            else hi = mid - 1;
        }

        return pivot;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if (start > end) return -1;

        int mid = (start + end) / 2;

        if (nums[mid] == target) return mid;

        if (target > nums[mid]) return binarySearch(nums, mid + 1, end, target);

        return binarySearch(nums, start, mid - 1, target);
    }
}
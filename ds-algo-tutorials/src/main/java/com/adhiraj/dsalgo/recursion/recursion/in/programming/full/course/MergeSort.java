package com.adhiraj.dsalgo.recursion.recursion.in.programming.full.course;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[] {100, 5, 2, 200, -1};
        System.out.print("Original Array :: ");
        printArr(arr);
        mergeSort(arr);
        System.out.print("Sorted Array :: ");
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int i = start, j = mid + 1, k = 0;
        int[] aux = new int[end - start + 1];

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j])
                aux[k++] = arr[i++];
            else
                aux[k++] = arr[j++];
        }
        while (i <= mid) {
            aux[k++] = arr[i++];
        }
        while (j <= end) {
            aux[k++] = arr[j++];
        }
        k = 0;
        for (int l = start; l <= end; l++, k++) {
            arr[l] = aux[k];
        }
    }
}

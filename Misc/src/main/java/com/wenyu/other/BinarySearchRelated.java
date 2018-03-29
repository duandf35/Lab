package com.wenyu.other;

import com.wenyu.CanRun;

/**
 * @author Wenyu
 * @since 11/8/16
 */
public class BinarySearchRelated implements CanRun {

    @Override
    public void run() {
        int[] arr1 = new int[] { 5, 7, 9, 13, 17, 19 };
        int[] arr2 = new int[] { 7, 13, 15, 19, 21, 23 };

        int toFind = 19;

        int[] sep1 = new int[] {};
        int[] sep2 = new int[] { 19 };
        int[] sep3 = new int[] { 18, 19 };
        int[] sep4 = new int[] { 19, 20 };
        int[] sep5 = new int[] { 17, 18, 19 };
        int[] sep6 = new int[] { 19, 20, 21 };
        int[] sep7 = new int[] { 20, 21, 22, 23 };

        int[][] bsTest = new int[][] { arr1, sep1, sep2, sep3, sep4, sep5, sep6, sep7 };

        for (int i = 0; i < bsTest.length; i++) {
            System.out.println("found = " + toFind + " at index = " + binarySearch(bsTest[i], 0, bsTest[i].length - 1, toFind) + " in [ " + arrToString(bsTest[i]) + " ]");
        }

        System.out.println("\n\nmedian value = " + medianOfTwoSortedArrays(arr1, arr2) + " of two sorted arrays [ " + arrToString(arr1) + " ] and [ " + arrToString(arr2) + " ]");
    }

    private int binarySearch(int[] arr, int from, int to, int toFind) {
        if (to == from && arr[to] == toFind) {
            return to;
        } else if (to < from) {
            return -1;
        }

        // e.g. even number of elements, from 5 to 10
        // [..., 5, 6, 7, 8, 9, 10, ...]
        // midIndex = (to - from) / 2 + from = 7
        // e.g. odd number of elements, from 5 to 9
        // [..., 5, 6, 7, 8, 9, ...]
        // midIndex = (to - from) / 2 + from = 7
        int midIndex = (to - from) / 2 + from;
        int midValue = arr[midIndex];

        if (midValue == toFind) {
            return midIndex;
        } else if (midValue > toFind) {
            return binarySearch(arr, from, midIndex - 1, toFind);
        } else { // midIndex < toFind
            return binarySearch(arr, midIndex + 1, to, toFind);
        }
    }

    private int medianOfTwoSortedArrays(int[] arr1, int[] arr2) {
        return -1;
    }

    private String arrToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < arr.length; j++) {
            sb.append(arr[j]);
            if (j < arr.length - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}

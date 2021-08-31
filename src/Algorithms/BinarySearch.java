package Algorithms;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        // write your code here
        int[] arr = {0,0,1,2,3,3,4,7,7,8};
        int[][] arr2D = {{1,2,3}, {7,8,9}};
        int target = 7;
        BinarySearch obj = new BinarySearch();

//        System.out.println(obj.BinarySearch(arr, target));
//        System.out.println(Arrays.toString(obj.binarySearchSortedMatrix(arr2D, target)));
//        System.out.println(obj.closestSearch(arr, 1));
//        System.out.println(Arrays.toString(obj.kClosest(arr, 3, 5)));
//        printInt(obj.smallestLargerThanTarget(new int[] {10,16,17,18,26,28,30,33,35,36,38,41,42,46,49,54,58,60,63,64}, 30));
        printInt(obj.missingNumber(new int[] {7,8,9,11}));
    }

    private static void printInt(int num) {
        System.out.println(num);
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    int binarySearch(int[] arr, int target) {
        // null check
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] == target) {
                return mid;
            }
            else if(arr[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return -1;
    }

    int[] binarySearchSortedMatrix(int[][] arr, int target) {
        // null check
        if(arr == null || arr.length == 0) {
            return new int[] {-1, -1};  // no find
        }

        int numRows = arr.length;
        int numCols = arr[0].length;
        int left = 0;  // left border, inclusive; could be called i
        int right = numRows * numCols - 1;  // right border, inclusive; called j

        while(left <= right) {
            int mid = (left + right) / 2;
            int trow = mid / numCols;
            int tcol = mid % numCols;

            int num = arr[trow][tcol];
            if(num == target) {
                return new int[] {trow, tcol};
            }
            else if(num < target) {
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return new int[] {-1, -1};  // target not found
    }


    int closestSearch(int[] array, int target) {
        // corner cases
        if(array == null || array.length == 0) {
            return -1;
        }

        int i = 0, j = array.length - 1;

        while(i < j - 1) {
            int mid = (i + j) / 2;
            if(array[mid] > target) {
                j = mid;
            }
            else {
                i = mid;
            }
        }

        // find closer one in two
        if( Math.abs(array[i] - target) < Math.abs(array[j] - target)) {
            return i;
        }
        else {
            return j;
        }
    }

    int firstOccurrence(int[] array, int target) {
        if(array == null || array.length == 0) {
            return -1;
        }

        int i = 0, j = array.length - 1;

        while(i < j - 1) {
            int mid = (i + j) / 2;
            if(array[mid] == target) {
                j = mid;
            }
            else if(array[mid] < target) {
                i = mid;
            }
            else {
                j = mid;
            }
        }

        // need to post-process i and j, i+1=j; or i=j;; 2 scenarios possible
        if(array[i] == target) {
            return i;
        }
        if(array[j] == target) {
            return j;
        }
        return -1;  // not i or j. target not found.
    }

    int lastOccurrence(int[] array, int target) {
        if(array == null || array.length == 0) {
            return -1;
        }

        int i = 0, j = array.length - 1;
        int mid;

        while(i < j - 1) {
            mid = (i + j) / 2;
            if(array[mid] == target) {
                i = mid;
            }
            else if(array[mid] < target) {
                i = mid + 1;
            }
            else {
                j = mid - 1;
            }
        }

        if(array[j] == target) {
            return j;
        }
        if(array[i] == target) {
            return i;
        }
        return -1;
    }

    // find the k number of closest number to target in the array.
    int[] kClosest(int [] array, int target, int k) {
//        int idx = closestSearch(array, target);
//        System.out.println(idx + "==");

        // old routine, review
        // first found the closest element.
        if(array.length == 0 || k == 0) {
            return new int[k];
        }

        int i = 0, j = array.length - 1;
        int mid;

        while(i < j-1) {
            mid = (i+j) / 2;
            if(array[mid] == target) {
                j = mid;
            }
            else if(target < array[mid]) {
                j = mid;
            }
            else {
                i = mid;
            }
        }

        int idx;
        if( Math.abs(array[i] - target) < Math.abs(array[j] - target)) {
            idx = i;
        }
        else {
            idx = j;
        }

        int l = idx, r = idx;
        System.out.println("l found: " + l);

        int[] result = new int[k];
        result[0] = array[idx];

        for(int count = 1; count < k; count++) {
            if(l == 0) {
                r++;
                result[count] = array[r];
                continue;
            }
            if(r == array.length - 1) {
                l--;
                result[count] = array[l];
                continue;
            }

//            System.out.println("at index" + count + "::  comparing l;r" + l + r);

            if(Math.abs(array[l-1] - target) <= Math.abs(array[r+1] - target)) {
                l--;
                result[count] = array[l];
            }
            else {
                r++;
                result[count] = array[r];
            }
        }

        return result;
    }

    int smallestLargerThanTarget(int[] array, int target) {
        if(array == null || array.length == 0) {
            return -1;
        }

        int l = 0, r = array.length - 1, mid;

        while(l < r + 1) {
            mid = (l+r) / 2;

            // stopping condition
            System.out.println("Boundary is l - r: " + l + " -- " + r);
            if(array[l] > target) {
                return l;
            }

            // in case.... , r would be left to l. but won't matter.

            if(array[mid] <= target) {
                l = mid + 1;
            }
            else {
                r = mid;
            }

            // if(array[l] > target) {
            //   return l;
            // }
        }

        return -1;
    }

    // return the missing number. Not on lai code.
    int missingNumber(int[] array) {
        int l = 0, r = array.length - 1, mid;

        while(r - l > 1) {
            mid = (l+r) / 2;
            if( (mid-l) != (array[mid] - array[l]) ) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        return array[r] - 1;
    }

//    int searchInUnknown(Dictionary dict, int target) {
//        int STEP = 10;
//        int exploredIdx = 0;
//
//        int left = 0, right = 1;
//        while(dict.get(right) != null && (int)dict.get(right) < target) {
//            left = right;
//            right = 2 * right;
//        }
//
//        return BinarySearch(Dictionary dict, int left, int right) {
//            while(left <= right) {
//                int mid = (left+right) / 2;
//                if(dict.get(mid) == null || dict.get(mid) > target)
//            }
//        }
//
//    }

}
    
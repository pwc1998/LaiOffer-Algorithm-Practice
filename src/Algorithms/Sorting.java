package Algorithms;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
    public static void main(String[] args) {
        Sorting object = new Sorting();
//        printInt(object.fibonacci(-1));
//        printInt(object.fibonacci(0));
//        printInt(object.fibonacci(1));
//        printInt(object.fibonacci(2));
//        printInt(object.fibonacci(3));
//        printInt((int)object.power(0,0));
//        printInt((int)object.power(2,3));
//        printInt((int)object.power(0, 10));
//        printInt((int)object.power(-2,5));
//        int[] arr = {5, 2, 7, 4, 1, 3, 8, 6, 0};
//        int[] arr = {1,9,8,3,5};
        int[] arr = {1,3,0,3,2,3,2,3,0,0,5,0,0,0,0,0};
//        printArray(object.selectionSort(new int[] {-1, -3, 7, 4}));
//        printArray(object.mergeSort(arr));
//        printArray(object.quickSort(arr));
        printArray(object.moveZero(arr));
    }

    private static void printInt(int num) {
        System.out.println(num);
    }
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // recursion easy practice
    int fibonacci(int k) {
        // base case
        if(k <= 0) {
            return 0;
        }
        if(k == 1) {
            return 1;
        }

        // recursive step
        return fibonacci(k-1) + fibonacci(k-2);
    }

    long power(int n, int k) {  // n^k
        // base case
        if (k == 0) {
            return 1;
        }

        // recursive step
        long halfResult = power(n, k / 2);
        if (k % 2 == 0) {
            return halfResult * halfResult;
        } else {
            return halfResult * halfResult * n;
        }
    }

    int[] selectionSort(int[] array) {
        if(array == null || array.length == 0) {
            return array;
        }

//        int[] result = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            int minIdx = i;
            for(int j = i + 1; j < array.length; j++) {
                if(array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }

            if(array[i] != array[minIdx]) {
//                // swap
                int temp = array[i];
                array[i] = array[minIdx];
                array[minIdx] = temp;
            }
        }

        return array;
    }

    int[] mergeSort(int[] arr) {
        if(arr == null || arr.length == 0) {
            return arr;
        }

        // base case
        if(arr.length == 1) {
            return arr;
        }

        // recursive step
        int leftLen = arr.length / 2;
        int rightLen = arr.length - leftLen;

        int[] left = new int[leftLen];
        int[] right = new int[rightLen];
        for(int i = 0; i < leftLen; i++) {
            left[i] = arr[i];
        }
        for(int i = 0; i < rightLen; i++) {
            right[i] = arr[leftLen + i];
        }

        int[] leftResult = mergeSort(left);
        int[] rightResult = mergeSort(right);

        // combine them together; move smaller one from either array
        int lidx = 0, ridx = 0;
        for(int i = 0; i < arr.length; i++) {
            // at left end
            if(lidx == leftLen) {
                arr[i] = rightResult[ridx];
                ridx++;
                continue;
            }
            if(ridx == rightLen) {
                arr[i] = leftResult[lidx];
                lidx++;
                continue;
            }

            // move smaller of two
            if(leftResult[lidx] <= rightResult[ridx]) {
                arr[i] = leftResult[lidx];
                lidx++;
            }
            else {
                arr[i] = rightResult[ridx];
                ridx++;
            }
        }

        return arr;
    }

    int[] quickSort(int[] arr) {
        // 1. pick pivot (randomly) and partition
        // 2. recursion on left and right. (use original array, after which would be sorted).
        //    2.1. base case, when array is 1 digit, return itself.

        // null check
        if(arr == null || arr.length == 0) {
            return arr;
        }

        quickSortHelper(arr, 0, arr.length - 1);

        return arr;
    }
    // left and right index of range, both inclusive
    private void quickSortHelper(int[] arr, int left, int right) {
        // base case
        if(left == right) {
            return;
        }

        // recursive step (1. picl pivot & partition; 2. recursively call)
        int pivot = pickPivot(left, right);
        int pivotAt = partition(arr, left, right, pivot);

        if(pivotAt-1 >= left) {
            quickSortHelper(arr, left, pivotAt-1);  // to the left
        }
        if(pivotAt+1 <= right) {
            quickSortHelper(arr, pivotAt + 1, right);  // to the right
        }
    }

    private int pickPivot(int left, int right) {
        Random random = new Random();
        int index = random.nextInt(right - left);
        return left + index;
    }
    // return index of pivot.
    private int partition(int[] arr, int left, int right, int pivotIndex) {
        // 1. substitute pivot with right most
        // 2. i & j going inward.
        // 3. swap rightmost (pivot) to ??
        // return ?? index

        int pivotVal = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int i = left, j = right-1;
        while(i <= j) {
            if(arr[i] < pivotVal) {
                i++;
                continue;
            }
            else {
                swap(arr, i, j);
                j--;
            }
        }

        swap(arr, i, right);
        return i;
    }
    private void swap(int[] arr, int aIdx, int bIdx) {
        int temp = arr[aIdx];
        arr[aIdx] = arr[bIdx];
        arr[bIdx] = temp;
    }

    int[] moveZero(int[] arr) {
        // assumption array is not null
        int i = 0, j = arr.length - 1;
        while(i <= j) {  // also could be i<=j;; no need to compare when i=j; last element, doesn matter
            if(arr[j] == 0) {
                j--;
                continue;
            }
            if(arr[i] == 0) {
                swap(arr, i, j);
                j--;
            }
            i++;
        }
        return arr;
    }
}

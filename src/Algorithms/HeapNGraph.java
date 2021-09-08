package Algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapNGraph {

    public static void main(String[] args) {
//        printInt(5);
//        int[] arr = {1,2,3,8,5,6,4,7,9};
        printInt(new Integer(args[0]));
        int[] arr = {6,5,4,3,2,1};
        int[][] matrix = {{1,3,5,7},
                {2,4,8,9},
                {3,5,11,15},
                {6,8,13,18}};
        HeapNGraph obj = new HeapNGraph();
//        printArray(obj.kSmallest(arr, 3));
        printInt(obj.kthSmallest(matrix, 5));
    }

    private static void printInt(int num) {
        System.out.println(num);
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static void printList(List<Integer > list) {
        System.out.println(list.toString());
    }
    private static void printBool(Boolean bool) {
        System.out.println(bool);
    }

    int[] kSmallest(int[] array, int k) {
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        for(int i = 0; i < array.length; i++) {
//            pq.offer(array[i]);
//        }
//
//        int[] result = new int[k];
//        for(int i = 0; i < k; i++) {
//            result[i] = pq.poll();
//        }
//
//        return result;

        if(k == 0) return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < k; i++) {
            pq.offer(array[i]);
        }
        for(int i = k; i < array.length; i++) {
            if (pq.peek() > array[i]) {
                pq.poll();
                pq.offer(array[i]);
            }
        }

        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
            result[k-1-i] = pq.poll();
        }

        return result;
    }

    // max-heap implementation
    // TODO: do a BFS search version!
    int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(pq.size() == k) {
                    int num = matrix[i][j];
                    int peek = pq.peek();
                    if(num < peek) {
                        pq.poll();
                        pq.offer(num);
                    }
                }
                else {
                    pq.offer(matrix[i][j]);
                }
            }
        }

        return pq.peek();
    }


}

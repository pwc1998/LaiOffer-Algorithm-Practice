package Algorithms;

import javafx.scene.layout.Priority;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapNGraph {

    public static void main(String[] args) {
        printInt(5);
//        int[] arr = {1,2,3,8,5,6,4,7,9};
        int[] arr = {6,5,4,3,2,1};
        HeapNGraph obj = new HeapNGraph();
        printArray(obj.kSmallest(arr, 3));
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


}

package Algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapNGraph {

    public static void main(String[] args) {
//        printInt(5);
//        int[] arr = {1,2,3,8,5,6,4,7,9};
//        printInt(new Integer(args[0]));
        int[] arr = {6,5,4,3,2,1};
        int[][] matrix = {{1,3,5,7},
                          {2,4,8,9},
                          {3,5,11,15},
                          {6,8,13,18}};
        HeapNGraph obj = new HeapNGraph();
//        printArray(obj.kSmallest(arr, 3));
        printInt(obj.kthSmallestBFS(matrix, 7));
        obj.test();
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

    // KEY POINT HERE: STATIC
    static class Unit {
        int col;
        int row;
        int val;

        Unit(int row, int col, int val) {
            this.col = col;
            this.row = row;
            this.val = val;
        }
    }

    static class UnitComparator implements Comparator<Unit> {
        public int compare(Unit u1, Unit u2) {
//            return u1.val - u2.val;  // min heap
            if(u1.val == u2.val) return 0;
            return u1.val < u2.val ? -1 : 1;
//            return u2.val - u1.val;  // max heap
        }
    }

    int kthSmallestBFS(int[][] matrix, int k) {
        PriorityQueue<Unit> minHeap = new PriorityQueue<>(new UnitComparator());
        minHeap.offer(new Unit(0, 0, matrix[0][0]));
        int count = 0;
        // we need to keep a matrix to determine who has been already generated
        boolean[][] generated = new boolean[matrix.length][matrix[0].length];
        generated[0][0] = true;
        while(!minHeap.isEmpty()) {
            // expand and generate
            Unit curr = minHeap.remove();
            count++;
            if (count == k) return curr.val;
//            System.out.println("EXPANDED" + curr.val);


            if (curr.col+1<matrix[0].length && !generated[curr.row][curr.col + 1]) {
                minHeap.offer(new Unit(curr.row, curr.col+1, matrix[curr.row][curr.col + 1]));
                generated[curr.row][curr.col+1] = true;
//                System.out.println("ROW" + matrix[curr.row][curr.col+1]);
            }
            if (curr.row+1<matrix.length && !generated[curr.row + 1][curr.col]){
                minHeap.offer(new Unit(curr.row + 1, curr.col, matrix[curr.row + 1][curr.col]));
                generated[curr.row+1][curr.col] = true;
//                System.out.println("COL" + matrix[curr.row + 1][curr.col]);
            }
        }

        return -1;
    }

    void test() {
        PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;  // i1 smaller, swap! then MAX!!!
            }
        });

        max.offer(1);
        max.offer(5);
        System.out.println(max.poll());
        System.out.println(max.poll());
    }


}

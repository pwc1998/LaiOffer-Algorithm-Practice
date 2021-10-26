package Algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;

public class StringNHashTable {
    public static void main(String[] args) {
        StringNHashTable obj = new StringNHashTable();
        int[] a = {0,1,2,3};
        int[] b = {2,7};

        String exclude = "abc";
        String str = "aabcdefffffffffffffffffffgabcdEFGGGGGGGGG";

//        printList(obj.common(a, b));
//        System.out.println(obj.remove(str, exclude));
//        printStr(obj.removeSpaces("      abc bbb    d  "));
//        printStr(obj.deDup(str));
//        printStr(obj.deDupHard("abbbaaccz"));
//        printInt(obj.countWaysToSplitFast("gfgfgfgfgfgfgfgfgfgfgfgfgfgfgf"));  // answer 387
        printInt(obj.testMod());
    }
    private static void printInt(int num) {
        System.out.println(num);
    }
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    private static void printList(List list) {
        System.out.println(list.toString());
    }
    private static void printBool(Boolean bool) {
        System.out.println(bool);
    }
    private static void printStr(String str) { System.out.println(str); }

    int testMod() {
        long mod = 1_000_000_007;
        return 1_00;
    }

    int countWaysToSplitFast(String s) {
        // using two dividers
        char[] in = s.toCharArray();
        int count = 0;
        for(int i = 0; i < s.length()-2; i++) {
            for(int j = i+1; j < s.length()-1; j++) {
                List<String> out = new ArrayList<>();
                out.add(Arrays.copyOfRange(in, 0, i+1).toString());
                out.add(Arrays.copyOfRange(in, i+1, j+1).toString());
                out.add(Arrays.copyOfRange(in, j+1, in.length).toString());
                if(checkAllDifferent(out)) count++;
            }
        }
        return count;
    }

    int countWaysToSplit(String s) {
        List<List<String>> splitted = new ArrayList();
        List<String> one = new ArrayList();
        DFSsplit(new StringBuilder(s), 0, one, splitted);
        // System.out.println(splitted);
        int count = 0;
        for(int i = 0; i < splitted.size(); i++) {
            // System.out.println(splitted.get(i));
            if(checkAllDifferent(splitted.get(i))) count++;
        }

        return count;
    }

    void DFSsplit(StringBuilder s, int level, List<String> oneSplit, List<List<String>> splitted) {
        // base case
        if(level == 3) {
            // System.out.println(oneSplit);
            splitted.add(new ArrayList(oneSplit));
            // System.out.println(splitted);
        }
        if(level == 2) {
            oneSplit.add(s.toString());
            DFSsplit(s, level+1, oneSplit, splitted);
            oneSplit.remove(oneSplit.size()-1);
        }

        // recursive step
        for(int i = 0; i < s.length()-1; i++) {
            oneSplit.add(s.substring(0, i+1));
            StringBuilder curr = new StringBuilder(s);
            DFSsplit(s.delete(0,i+1), level+1, oneSplit, splitted);
            s = curr;
            oneSplit.remove(oneSplit.size()-1);
            // DFSsplit(s.substring(i+1, ), level+1, oneSplit, splitted);
        }
    }

    boolean checkAllDifferent(List<String> one) {
//        System.out.println(one.get(0));
//        System.out.println(one.get(1));
//        System.out.println(one.get(2));
//        System.out.println(one.get(0).equals(one.get(1)));
//        System.out.println(one.get(2).equals(one.get(1)));
        String ab = one.get(0) + one.get(1);
        String bc = one.get(1) + one.get(2);
        String ca = one.get(2) + one.get(0);
        if(ab.equals(bc) || bc.equals(ca) || ab.equals(ca)) return false;
        return true;
    }

    // Top K frequent wor
//    String[] topKFrequent(String[] combo, int k) {
//        HashMap<String, Integer> words = new HashMap<>();
//        for(int i = 0; i < combo.length; i++) {
//            Integer count = words.get(combo[i]);
//            if(count==null) {
//                words.put(combo[i], 1);
//            }
//            else{
//                words.replace(combo[i], ++count);
//            }
//        }
//
//        // IMPORTANT NEW STUFF HERE
//        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,
//                new Comparator)
//    }

    int missing(int[] array) {
//        boolean[] numInside = new boolean[array.length+1];
//        for(int i = 0; i < array.length; i++) {
//            numInside[array[i]-1] = true;
//        }
//        for(int i = 0; i < numInside.length; i++) {
//            if(numInside[i] == false) {
//                return i+1;
//            }
//        }
//
//        return -1; // error.

        // Hash table implementation
        HashMap<Integer, Boolean> nums = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            nums.put(array[i], true);
        }
        for(int i = 1; i <= array.length+1; i++) {
            if(nums.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    List<Integer> common(int[] A, int[] B) {
        // ascending arrays
        // two pointers
//        int p1 = 0, p2 = 0;
        List<Integer> result = new ArrayList<>();
//
//        while(p1 < A.length) {
//            System.out.println("Once"+p1+p2);
//            // check p2 not over length
//            if(p2 == B.length) return result;
//            if(A[p1] > B[p2]) {
//                p2++;
//            }
//            else if(A[p1] < B[p2]) {
//                p1++;
//            }
//            else if(A[p1] == B[p2]) {
//                result.add(A[p1]);
//                p1++;
//                p2++;
//            }
//        }
//
//        return result;

        // Hashtable implementation
        HashMap<Integer, Boolean> numExist = new HashMap<>();
        if(A.length > B.length) {  // use A as ref
            int[] temp = B;
            B = A;
            A = temp;
        }

        for(int i = 0; i < A.length; i++) {
            numExist.put(A[i], true);
        }

        for(int i = 0; i < B.length; i++) {
            if(numExist.get(B[i]) != null) {
                result.add(B[i]);
            }
        }
        return result;
    }


    String remove(String input, String t) {
        StringBuilder str = new StringBuilder(input);
        int i = 0;  // all letters to the left of it, keep (not including i)
        int j = 0;  // current processing
        HashMap<Character, Boolean> exclude = new HashMap<>();
        for(int k = 0; k < t.length(); k++) {
            exclude.put(t.charAt(k), true);
        }

        while(j < input.length()) {
            char curr = str.charAt(j);
            if(exclude.get(curr) == null) {  // to keep
                str.setCharAt(i, curr);
                i++;
                j++;
            }
            else {
                j++;
            }
        }

        return str.substring(0, i);
    }

    String removeSpaces(String input) {
        char[] seq = input.toCharArray();
        int i = 0, j = 0;
        // processing leading spaces
        while(j < input.length()) {
            if(seq[j] == ' ') j++;
            else break;
        }
        while(j < input.length()-1) {
            if(seq[j] == ' ') {  //check next to see if more spaces
                if(seq[j+1] == ' ') {
                    j++;
                }
                else {
                    seq[i] = seq[j];
                    i++;
                    j++;
                }
            }
            else {
                seq[i] = seq[j];
                i++;
                j++;
            }
        }

        if(j == input.length()) return new String();
        if(seq[j] != ' ') {
            seq[i] = seq[j];
            i++;
        }

        return new String(seq, 0, i);
    }

    String deDup(String input) {
        if(input == null) return null;
        char[] seq = input.toCharArray();

        int i = 0;  // keep everything to the left (itself exclusive)
        int j = 0;
        while(j < input.length()) {
            char curr = seq[j];
            while(++j < input.length()) {
                if(seq[j] != curr) break;
            }

            seq[i] = curr;
            i++;
        }

        return new String(seq, 0, i);
    }

    String deDupHard(String input) {
//        char[] seq = input.toCharArray();
//        boolean[] deleted = new boolean[input.length()];
//
//        for(int i = 0; i < input.length(); i++) {
//            if(deleted[i]) continue;// not considered char
//
//            char curr = seq[i];
//            int currInd = i;
//
//            boolean goBack = false;
//            while(i+1 < input.length()) {
//                i++;
//                if(deleted[i]) {
////                    System.out.println(deleted[i]);
////                    System.out.println(seq[i]);
//                    continue;
//                }
//                if(seq[i] == curr) {
//                    deleted[i] = true;
//                    goBack = true;
//                }
//                else {
//                    i--;  // revert back
//                    break;
//                }
//            }
//            if(goBack) {
//                i = -1;
//                deleted[currInd] = true;
//            }
//        }
//
//        StringBuilder result = new StringBuilder();
//        for(int i = 0; i < deleted.length; i++) {
//            if(!deleted[i]) result.append(seq[i]);
//        }
//
//        return result.toString();

        // using in-place stack  (IMPLICIT STACK)
        char[] seq = input.toCharArray();
        int i = 0;  // default, take the first char
        int j = 1;  // start wwith the second char

        for(j = 1; j < input.length(); j++) {
            // if stack empty, or no duplicate, new char allowed
            if(i == -1 || seq[j] != seq[i]) {
//                i++;
//                seq[i] = seq[j];
                seq[++i] = seq[j];
            }
            else {
                i--;
                while(j+1 < input.length() && seq[j] == seq[j+1]) j++;
            }
        }
        return new String(seq, 0, i+1);
    }

}
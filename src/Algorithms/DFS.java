package Algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class DFS {
    public static void main(String[] args) {
        String set = "abc";
        DFS dfs = new DFS();
        printList(dfs.subSets(set));
        printList(dfs.combinations(1, new int[] {25,10,5,1}).get(0));
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

    /*
     * TWO questions for DFS: (DFS is recursion) (back tracking is a behavior of DFS)
     * 1. how many levels are in the recursion tree? NOT input tree
     * 2. how many different states would there be for each level?
     *
     * BELOW ARE ALL EXAMPLES OF  **back tracking**!!!
     */

    List<String> subSets(String set) {
        if(set == null) return new LinkedList<String>();
        List<String> result = new LinkedList<>();
        StringBuilder prefix = new StringBuilder();
        subsetHelper(set, 0, prefix, result);
        return result;
    }
    void subsetHelper(String set, int level, StringBuilder solutionPrefix, List<String> result) {
        // base case
        if(level == set.length()) {
//            System.out.println(level + "+++" + solutionPrefix.toString() + "---" + result.toString());
            result.add(solutionPrefix.toString());
            return;
        }

        // recursive step
        // case 1: do not add char
        subsetHelper(set, level+1, solutionPrefix, result);

        // case 2: add the char, recursive call, and delete the char
        solutionPrefix.append(set.charAt(level));
        subsetHelper(set, level+1, solutionPrefix, result);
        solutionPrefix.deleteCharAt(solutionPrefix.length()-1);

        // done.
    }

    // Find valid parentheses
    List<String> validParenthese(int n) {
        List<String> result = new LinkedList<>();
        StringBuilder prefix = new StringBuilder();
        validParentheseHelper(n, 0, 0, prefix, result);
        return result;
    }
    void validParentheseHelper(int n, int l, int r, StringBuilder prefix, List<String> result) {
        // base case
        if(l == n && r == n) {
            result.add(prefix.toString());
            return;
        }

        // recursive step
        // case one: add left (; only if there's not enough of them
        if(l < n) {
            prefix.append("(");
            validParentheseHelper(n, l+1, r, prefix, result);
            prefix.deleteCharAt(prefix.length()-1);
        }

        // case two: add right ); only if enough left (
        if(r < l) {  // if equal, then cannot add )
            prefix.append(")");
            validParentheseHelper(n, l, r+1, prefix, result);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    // combination of coins
    List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> possibility = new LinkedList<>();
        combinationsHelper(target, coins, 0, possibility, result);
        return result;
    }
    void combinationsHelper(int target, int[] coins, int level, List<Integer> possibility, List<List<Integer>> result) {
        // base case
        if(level == coins.length) {
            if(target == 0) {
                result.add(new LinkedList<>(possibility));
                return;
            }
            return;
        }

        // recursive step
        // each level, add a new type of coin
        int coinVal = coins[level];
        int maxNum = target / coinVal;
        for(int i = 0; i <= maxNum; i++) {
            possibility.add(i);
            combinationsHelper(target - i*coinVal, coins, level+1, possibility, result);
            possibility.remove(possibility.size()-1);
        }
    }

    public List<String> permutations(String input) {
        // Write your solution here
        if(input.length() == 0) {
            List<String> temp = new ArrayList<>();
            temp.add("");
            return temp;
        }
        List<String> result = new ArrayList<>();
        permutationsHelper(input.toCharArray(), 0, result);
        return result;
    }
    // 1. a total of #of chars levels
    // 2. each level, each branch represent the inclusion of a *remaining* char
    void permutationsHelper(char[] input, int level, List<String> result) {
        // base case
        if(level == input.length-1) {
            result.add(new String(input));
            return;
        }

        // recursive step: process for each branch
        for(int i = level; i < input.length; i++) {
            // pick any of the remaining char, at index level
            swapChar(input, i, level);
            permutationsHelper(input, level+1, result);
            swapChar(input, i, level);
        }
    }
    void swapChar(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

}

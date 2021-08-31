package com.company;
import java.util.ArrayList;

public class recursion_final {
    public static void main(String[] args) {
        ArrayList<Integer> arrlist = new ArrayList<Integer>();
        arrlist.add(5);
//        arrlist.add(10);
//        arrlist.add(25);
        System.out.println("number of ways: " + makeChange(arrlist, 7));
    }

    private static int makeChange(ArrayList<Integer> coins, int target) {
        // base case, target is 0. return 1, as this is a viable way to make change
        if (target == 0) {
            return 1;
        }


        int numWays = 0;
        for(int i = 0; i < coins.size(); i++) {
            int coin = coins.get(i);
            if(coin <= target) {
                numWays += makeChange(new ArrayList<Integer>(coins.subList(i, coins.size())), target - coin);
            }
        }
        return numWays;
    }
}

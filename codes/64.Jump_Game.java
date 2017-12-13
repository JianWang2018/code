/*
problem:
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

 Notice

This problem have two method which is Greedy and Dynamic Programming.

The time complexity of Greedy method is O(n).

The time complexity of Dynamic Programming method is O(n^2).

We manually set the small data set to allow you pass the test in both ways. This is just to let you learn how to use this problem in dynamic programming ways. If you finish it in dynamic programming ways, you can try greedy method to make it accept again.

Have you met this question in a real interview? Yes
Example
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

key:1) check every previous stairs if anyone can reach i
2)  the previous stair should also be reached
*/

// mine:
public class Solution {
    /*
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        // write your code here

        // inilize a f which represent if it can reach the last
        int n= A.length;
        int [] f =new int [n];

        f[n-1]=1;
        for (int i = n-2 ; i>=0;i--){
            for( int j =i ;j <=i+A[i];j++){
                if (j>n-1) break;
                if (f[j]==1) f[i]=1;
            }

        }
        return f[0]==1;
    }
}
// 9 chapter:


// version 1: Dynamic Programming
// 这个方法，复杂度是 O(n^2) 可能会超时，但是依然需要掌握。
public class Solution {
    public boolean canJump(int[] A) {
        boolean[] can = new boolean[A.length];
        can[0] = true;

        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + A[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }

        return can[A.length - 1];
    }
}


// version 2: Greedy
public class Solution {
    public boolean canJump(int[] A) {
        // think it as merging n intervals
        if (A == null || A.length == 0) {
            return false;
        }
        int farthest = A[0];
        for (int i = 1; i < A.length; i++) {
            if (i <= farthest && A[i] + i >= farthest) {
                farthest = A[i] + i;
            }
        }
        return farthest >= A.length - 1;
    }
}

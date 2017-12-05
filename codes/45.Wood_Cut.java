problem:

Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

 Notice

You couldn't cut wood into float length.

If you couldn't get >= k pieces, return 0.

Have you met this question in a real interview? Yes
Example
For L=[232, 124, 456], k=7, return 114.

key:build a count function and transfer to the last binary search problem

mine: 
public class Solution {
    /*
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // note that one wood can be cutted into several pieces, so l.length can be less than K
        // find the maximum of 
        int maxLen = Integer.MIN_VALUE;
        for (int i =0;i<L.length-1;i++){
            maxLen =Math.max(maxLen,L[i]);
        }
        int start =0, end =maxLen, mid;
        while (start+1<end){
            mid=start+(end - start)/2; 
            if (count(L,mid)>=k){
                start=mid;
            }
            else end =mid;
        }
        if(count(L,end)<k) return start;
        else return end;
    }
    // build a count function that find the number of cuts with length a
    private int count(int[] L,int length){
        int total = 0; 
        for (int i = 0; i<L.length;i++){
            total += L[i]/length;
        }
        return total; 
    }
}
9 chapter:


public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        int max = 0;
        for (int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }
        
        // find the largest length that can cut more than k pieces of wood.
        int start = 1, end = max;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(L, mid) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (count(L, end) >= k) {
            return end;
        }
        if (count(L, start) >= k) {
            return start;
        }
        return 0;
    }
    
    private int count(int[] L, int length) {
        int sum = 0;
        for (int i = 0; i < L.length; i++) {
            sum += L[i] / length;
        }
        return sum;
    }
}


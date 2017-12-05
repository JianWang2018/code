problem: 

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume NO duplicates in the array.

Have you met this question in a real interview? Yes
Example
[1,3,5,6], 5 → 2

[1,3,5,6], 2 → 1

[1,3,5,6], 7 → 4

[1,3,5,6], 0 → 0

key:if(target<=A[start]) return start;
        if (target>A[end]) return end+1;
        else return end; 

mine:
public class Solution {
    /*
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        if(A==null || A.length==0) return 0; 
        
        int start=0,end=A.length-1,mid; 
        while (start+1 <end){
            mid = start+(end-start)/2;
            if (A[mid]==target) return mid; 
            else if (A[mid]<target) start=mid;
            else end=mid;
            
        }
        
        // if target less or equal to A[start] return start
        // if target > end, insert after end, so end+1;
        //else between start and end, return end
        if(target<=A[start]) return start;
        if (target>A[end]) return end+1;
        else return end; 
    }
}
9 cahpter:

// version 1: find the first position >= target
public class Solution {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0, end = A.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (A[start] >= target) {
            return start;
        } else if (A[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }
}

// version 2: find the last position < target, return +1， 要特判一下target小于所有数组里面的元素

public class Solution {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0;
        int end = A.length - 1;
        int mid;
        
        if (target < A[0]) {
            return 0;
        }
        // find the last number less than target
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (A[end] == target) {
            return end;
        }
        if (A[end] < target) {
            return end + 1;
        }
        if (A[start] == target) {
            return start;
        }
        return start + 1;
    }
}



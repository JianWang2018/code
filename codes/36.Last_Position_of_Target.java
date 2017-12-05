Find the last position of a target in a list

key: two situation that start == mid, finally, first check end and then start

mine: 

class Solution {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        if (nums.length==0 || nums==null) return -1; 
        
        int start= 0, end = nums.length -1 ,mid; 
        while (start+1 <end){
            mid = start+(end-start)/2;
            if(nums[mid]==target) start = mid;// note here not return mid;
            
            else if (nums[mid]<target) start = mid; 
            
            else end = mid;
            
        }
        if (nums[end]==target) return end;
        if (nums[start]==target) return start; 
        return -1;
    }
}

9 chapter: 

// version 1: with jiuzhang template
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
                // or start = mid + 1
            } else {
                end = mid;
                // or end = mid - 1
            }
        }
        
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }
}

// version 2: without jiuzhang template
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid + 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        if (nums[start] == target) {
            return start;
        }
        else if(start != 0 && nums[start - 1] == target){
            return start - 1;
        }
        return -1;
    }
}


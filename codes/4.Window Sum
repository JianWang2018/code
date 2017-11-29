problem:

Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the sum of the element inside the window at each moving.

code: 
mine:
public class Solution {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length < k || k <= 0)
            return new int[0];

        int[] sums = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++)
            sums[0] += nums[i];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k-1];
        }
        return sums;
    }
}


9 chapter: 
public class Solution {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length < k || k <= 0)
            return new int[0];

        int[] sums = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++)
            sums[0] += nums[i];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k-1];
        }
        return sums;
    }
}





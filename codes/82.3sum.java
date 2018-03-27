Problem:

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Notice

Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

The solution set must not contain duplicate triplets.

Have you met this question in a real interview? Yes
Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)
(-1, -1, 2)
Given N = 3 and the array [0, 1, 3], return 2.

// mine: public class Solution {
    /*
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (numbers ==null || numbers.length<3) return res; 
        

        Arrays.sort(numbers);
        int n = numbers.length;
        for ( int i = 0 ; i < n -2; i ++){
            // skip the same first value 
            if ( i > 0 && numbers[i]==numbers[i-1]) continue;
            int left = i+1, right = n -1 ; 
             while ( left <right){
                 if ( numbers[left]+ numbers[right]==(-numbers[i])){
                     List<Integer> temp = new ArrayList<>();
                     temp.add(numbers[i]);
                     temp.add(numbers[left]);
                     temp.add(numbers[right]);
                     res.add(temp);
                     left++;
                     right --;
                     // skip the same value
                     while ( numbers[left]==numbers[left-1] && left<n-1) left++;
                     while (numbers[right]==numbers[right+1] && right >0) right --;
                 }
                 else if ( numbers[left]+ numbers[right]> (-numbers[i])) right --; 
                 else left++;
                 
             }
             
        }
        
        return res;
        
        
    }
}
// 9 chapter:/**

public class Solution {
    /**
     * @param nums : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        
        if (nums == null || nums.length < 3) {
            return results;
        }
        
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // skip duplicate triples with the same first numebr
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            
            twoSum(nums, left, right, target, results);
        }
        
        return results;
    }
    
    public void twoSum(int[] nums,
                       int left,
                       int right,
                       int target,
                       List<List<Integer>> results) {
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                ArrayList<Integer> triple = new ArrayList<>();
                triple.add(-target);
                triple.add(nums[left]);
                triple.add(nums[right]);
                results.add(triple);
                
                left++;
                right--;
                // skip duplicate pairs with the same left
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                // skip duplicate pairs with the same right
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
    }
}
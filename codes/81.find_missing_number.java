Problem:

Given an array contains N numbers of 0 .. N, find which number doesn't exist in the array.

Have you met this question in a real interview? Yes
Example
Given N = 3 and the array [0, 1, 3], return 2.

// mine: 
public class Solution {
    /*
     * @param nums: An array of integers
     * @return: An integer
     */
    public int findMissing(int[] nums) {
        // write your code here
        if (nums.length==0 || nums==null) return -1; 
        int n = nums.length; 
        
        int sum;
        
        sum =(n+1)*n/2;
        
        for (int num: nums){
            sum-=num;
        }
        
        return sum;
        
    }
};
// 9 chapter:/**
* 本参考程序来自九章算法，由 @九章算法 提供。版权所有，转发请注明出处。
* - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
* - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
* - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
* - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
*/ 

public class Solution {
    /**    
     * @param nums: an array of integers
     * @return: an integer
     */
    public int findMissing(int[] nums) {
        // write your code here
        int n = nums.length, i = 0;
        while (i<n) {
            while (nums[i]!=i && nums[i]<n) {
                int t = nums[i];
                nums[i] = nums[t];
                nums[t] = t;
            }
            ++i;
        }
        for (i=0; i<n; ++i)
            if (nums[i]!=i) return i;
        return n;
    }
}

//Math
public class Solution {
    /**    
     * @param nums: an array of integers
     * @return: an integer
     */
    public int findMissing(int[] nums) {
        long N = nums.length;
        long sum = N * (N + 1) / 2;
        for(int i : nums){
            sum -= i;
        }
        return (int)sum;
    }
}

//Xor
public int findMissing(int[] nums) {
    // write your code here
    int ans = 0;
    for (int i = 0; i <= nums.length; i++) {
        ans ^= i;
    }
    for (int i = 0; i < nums.length; i++) {
        ans ^= nums[i];
    }
    return ans;
}

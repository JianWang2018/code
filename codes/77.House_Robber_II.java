// After robbing those houses on that street, the thief has found himself a new
// place for his thievery so that he will not get too much attention. This time,
// all houses at this place are arranged in a circle. That means the first house
// is the neighbor of the last one. Meanwhile, the security system for these houses
// remain the same as for those in the previous street.
//
// Given a list of non-negative integers representing the amount of money of each
// house, determine the maximum amount of money you can rob tonight without alerting
// the police.

// key:

//mine :

class Solution {
    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];

        // choose start from the first hourse or start from the second hourse

        int [] f_rob_0 = new int [nums.length+1];
        int [] f_rob_1 = new int [nums.length+1];

        f_rob_0[0]= 0 ;
        f_rob_0[1]= nums[0] ;

        f_rob_1[0]=0;
        f_rob_1[1]=0;

        for(int i =2 ; i<=nums.length;i++){
            f_rob_0[i]=Math.max(f_rob_0[i-1],f_rob_0[i-2]+nums[i-1]);
            f_rob_1[i]=Math.max(f_rob_1[i-1],f_rob_1[i-2]+nums[i-1]);

        }
        return Math.max(f_rob_0[nums.length-1],f_rob_1[nums.length]);

    }
}

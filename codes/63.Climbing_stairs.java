/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Have you met this question in a real interview? Yes
Example
Given an example n=3 , 1+1+1=2+1=1+2=3

return 3
key:can use dp or two variable last and prev 


*/
// mine;
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        // write your code here

        if (n ==0 )
        return 0;
        if(n==1)
        return 1;

        int [] f = new int [n+1];

        f[0]= 1;
        f[1]=1 ;
        for (int i = 2; i <=n ; i++){
            f[i]=f[i-1]+ f[i-2];
        }

        return f[n];
    }
}

// 9 chapter;


public class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return n;
        }
        int last = 1, lastlast = 1;
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }
}

// Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
//
// Example 1:
// Input:
// A: [1,2,3,2,1]
// B: [3,2,1,4,7]
// Output: 3
// Explanation:
// The repeated subarray with maximum length is [3, 2, 1].
// Note:
// 1 <= len(A), len(B) <= 1000
// 0 <= A[i], B[i] < 100

// mine:
class Solution {
    public int findLength(int[] A, int[] B) {
        int res = 0 ;
        int [][] f= new int [A.length+1][B.length+1];
        for( int i = A.length-1; i>=0 ; i --){
            for(int j = B.length-1 ; j>= 0; j--){
                if(A[i]==B[j]){
                    f[i][j]=f[i+1][j+1]+1;
                    res=Math.max(res,f[i][j]);
                }
            }
        }

        return res;
    }
}

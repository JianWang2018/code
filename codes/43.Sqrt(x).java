problem:

Implement int sqrt(int x).

Compute and return the square root of x.

key: from 1 to x, find the last item that item*item<x 

mine:
public class Solution {
    /*
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        if (x==0) return 0 ; 
        if(x<0) return -1; 
        
        int start=1, end =x,mid;
        while (start+1<end){
            mid = start+(end-start)/2;
            if (Math.pow(mid,2)==x) return mid;
            if (Math.pow(mid,2)<x) start=mid; 
            if (Math.pow(mid,2)>x) end=mid; 
            
        }
        // find the last int that the square is less than x
        if(Math.pow(end,2)<=x) return end;
         return start;
        
    }
}

9 chapter: 

class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // find the last number which square of it <= x
        long start = 1, end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (end * end <= x) {
            return (int) end;
        }
        return (int) start;
    }
}



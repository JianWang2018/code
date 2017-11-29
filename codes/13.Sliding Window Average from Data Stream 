problem:

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

code: 
mine :
public class MovingAverage {
    /*
    * @param size: An integer
    */
    private Queue<Integer> que; 
    private double sum = 0; 
    private int size; 
    
    public MovingAverage(int size) {
        que = new LinkedList <Integer> ();
        this.size=size;
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        // write your code here
        
        sum+=val ;
        if(que.size()==size){
            sum-=que.poll();
        }
        que.offer(val);

        return sum/que.size();
    }
}

9 chapter: 

public class MovingAverage {

    private Queue<Integer> que;
    private double sum = 0;
    private int size;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        que = new LinkedList<Integer>();
        this.size = size;
    }
    
    public double next(int val) {
        // Write your code here
        sum += val;
        if (que.size() == size) {
            sum = sum - que.poll();
        }
        que.offer(val);
        return sum / que.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param = obj.next(val);
 */

// version: 高频题班
//非滚动
public class MovingAverage {
    /**
     * Initialize your data structure here.
     */
    int id, size;
    double[] sum;

    MovingAverage(int s) {
        id = 0;
        size = s;
        sum = new double[1000000];   //this is not final version
    }

    public double next(int val) {
        // Write your code here
        id++;
        sum[id] = sum[id - 1] + val;
        if (id - size >= 0) {
            return (sum[id] - sum[id - size]) / size;
        } else {
            return sum[id] / id;
        }
    }
}

//滚动
public class MovingAverage {
    /**
     * Initialize your data structure here.
     */
    int id, size;
    double[] sum;

    MovingAverage(int s) {
        id = 0;
        size = s;
        sum = new double[size + 1];
    }

    int mod(int x) {
        return x % (size + 1);
    }

    public double next(int val) {
        // Write your code here
        id++;
        sum[mod(id)] = sum[mod(id - 1)] + val;
        if (id - size >= 0) {
            return (sum[mod(id)] - sum[mod(id - size)]) / size;
        } else {
            return sum[mod(id)] / id;
        }

    }
}



problem: 

给一个按照升序排序的正整数数组。这个数组很大以至于你只能通过固定的接口 ArrayReader.get(k) 来访问第k个数。(或者C++里是ArrayReader->get(k))，并且你也没有办法得知这个数组有多大。找到给出的整数target第一次出现的位置。你的算法需要在O(logk)的时间复杂度内完成，k为target第一次出现的位置的下标。

如果找不到target，返回-1。

key: use double increasing method

mine:
public class Solution {
    /**
     * @param reader: An instance of ArrayReader can read number by index.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {

        
        int index = 1;
        while (reader.get(index - 1) < target) {
            index = index * 2;
        }
        
        int start = index/2, end = index - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (reader.get(start) == target) {
            return start;
        }
        
        if (reader.get(end) == target) {
            return end;
        }
        
        return -1;
    }
}
9 chapter:

public class Solution {
    /**
     * @param reader: An instance of ArrayReader can read number by index.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        // Algorithm:
        // 1. get the index that ArrayReader.get(index) >= target in
        //    O(logk)
        // 2. Binary search the target between 0 and index
        
        int index = 1;
        while (reader.get(index - 1) < target) {
            index = index * 2;
        }
        
        int start = 0, end = index - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (reader.get(start) == target) {
            return start;
        }
        
        if (reader.get(end) == target) {
            return end;
        }
        
        return -1;
    }
}

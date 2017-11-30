problem: 
Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Have you met this question in a real interview? Yes
Example
Insert [2, 5] into [[1,2], [5,9]], we get [[1,9]].

Insert [3, 4] into [[1,2], [5,9]], we get [[1,2], [3,4], [5,9]].

code: 

mine: 
class Solution {
    /**
     * Insert newInterval into intervals.
     *
     * @param intervals:   Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // write your code here
        List<Interval> ans = new ArrayList<>();
        
        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start) {
            idx++;
        }
        //note: list.add(index, item)
        //fist add the interval
        intervals.add(idx, newInterval);

        Interval last = null;
        // next merge the overlap intervals
        for (Interval item : intervals) {
            if (last == null || last.end < item.start) {
                ans.add(item);
                last = item;
            } else {
            // note: new end the maximum of two
                last.end = Math.max(last.end, item.end); // Modify the element already in list
            }
        }
        return ans;
    }
}

9 chapter: 

public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null || intervals == null) {
            return intervals;
        }

        List<Interval> results = new ArrayList<Interval>();
        int insertPos = 0;

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                results.add(interval);
                insertPos++;
            } else if (interval.start > newInterval.end) {
                results.add(interval);
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }

        results.add(insertPos, newInterval);

        return results;
    }
}

// version: 高频题班
class Solution {
    /**
     * Insert newInterval into intervals.
     *
     * @param intervals:   Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // write your code here
        List<Interval> ans = new ArrayList<>();
        
        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start) {
            idx++;
        }
        intervals.add(idx, newInterval);

        Interval last = null;
        for (Interval item : intervals) {
            if (last == null || last.end < item.start) {
                ans.add(item);
                last = item;
            } else {
                last.end = Math.max(last.end, item.end); // Modify the element already in list
            }
        }
        return ans;
    }
}

problem;

Given a collection of intervals, merge all overlapping intervals.

Have you met this question in a real interview? Yes
Example
Given intervals => merged intervals:

[                     [
  [1, 3],               [1, 6],
  [2, 6],      =>       [8, 10],
  [8, 10],              [15, 18]
  [15, 18]            ]
]


code: 

mine:
class Solution {
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        List<Interval> ans = new ArrayList<>();

        intervals.sort(Comparator.comparing(i -> i.start));  //lambda 匿名函数：输入i  返回i.start

        Interval last = null;
        for (Interval item : intervals) {
            if (last == null || last.end < item.start) {
                ans.add(item);
                last = item;
            } else {
            //note : the new last is the max of new item end and original interval end
                last.end = Math.max(last.end, item.end); // Modify the element already in list
            }
        }
        return ans;
    }
}
9 chapter: 


public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        
        Collections.sort(intervals, new IntervalComparator());       
  
        List<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end ){
                last.end = Math.max(last.end, curt.end);
            }else{
                result.add(last);
                last = curt;
            }
        }
        
        result.add(last);
        return result;
    }
    
    
    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }

}


// version: 高频题班
class Solution {
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        List<Interval> ans = new ArrayList<>();

        intervals.sort(Comparator.comparing(i -> i.start));  //lambda 匿名函数：输入i  返回i.start

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


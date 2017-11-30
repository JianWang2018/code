problem: 
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Have you met this question in a real interview? Yes
Clarification
Your algorithm should run in O(n) complexity.

Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

code: 

mine:
public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        // write you code here
        Set<Integer> set = new HashSet<>();
        for (int item : num) {
            set.add(item);
        }

        int ans = 0;
        for (int item : num) {
            if (set.contains(item)) {
                set.remove(item);

                int pre = item - 1;
                int next = item + 1;
                while (set.contains(pre)) {
                    set.remove(pre);
                    pre--;
                }
                while (set.contains(next)) {
                    set.remove(next);
                    next++;
                }
                ans = Math.max(ans, next - pre - 1);
            }
        }
        return ans;
    }
}
9 chapter: 

public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
            }
            
            int up = nums[i] + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }
            
            longest = Math.max(longest, up - down - 1);
        }
        
        return longest;
    }
}

// version: 高频题班
public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        // write you code here
        Set<Integer> set = new HashSet<>();
        for (int item : num) {
            set.add(item);
        }

        int ans = 0;
        for (int item : num) {
            if (set.contains(item)) {
                set.remove(item);

                int pre = item - 1;
                int next = item + 1;
                while (set.contains(pre)) {
                    set.remove(pre);
                    pre--;
                }
                while (set.contains(next)) {
                    set.remove(next);
                    next++;
                }
                ans = Math.max(ans, next - pre - 1);
            }
        }
        return ans;
    }
}




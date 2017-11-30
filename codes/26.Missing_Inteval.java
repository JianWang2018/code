problem:

Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Have you met this question in a real interview? Yes
Example
Given nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
return ["2", "4->49", "51->74", "76->99"].

code:

mine:
public class Solution {
    /**
     * @param nums  a sorted integer array
     * @param lower an integer
     * @param upper an integer
     * @return a list of its missing ranges
     */

    /*
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // Write your code here
        List<String> ans = new ArrayList<>();

        addRange(ans, lower, nums[0] - 1);

        for (int i = 1; i < nums.length; i++) {
            addRange(ans, nums[i - 1] + 1, nums[i] - 1);
        }
        addRange(ans, nums[nums.length - 1] + 1, upper);

        return ans;
    }

    void addRange(List<String> ans, int st, int ed) {
        if (st > ed) {
            return;
        }
        if (st == ed) {
            ans.add(st + "");
            return;
        }
        ans.add(st + "->" + ed);
    }*/

    
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // Write your code here
        List<String> ans = new ArrayList<>();
        if (nums.length == 0) {
            addRange(ans, lower, upper);
            return ans;
        }

        addRange(ans, lower, (long) nums[0] - 1);

        for (int i = 1; i < nums.length; i++) {
            addRange(ans, (long) nums[i - 1] + 1, (long) nums[i] - 1);
        }
        addRange(ans, (long) nums[nums.length - 1] + 1, upper);

        return ans;
    }

    void addRange(List<String> ans, long st, long ed) {
        if (st > ed) {
            return;
        }
        if (st == ed) {
            ans.add(st + "");
            return;
        }
        ans.add(st + "->" + ed);
    }
    
}
9 chapter: 

public class ValidWordAbbr {

    private Map<String,Set<String>> d;

    // @param dictionary a list of word
    public ValidWordAbbr(String[] dictionary) {
        // Write your code here
        d = new HashMap<String, Set<String>>();
        for (int i = 0;i < dictionary.length; i++) {
            String w = dictionary[i];
            String abbr = getAbbr(w); 
        
            if (d.containsKey(abbr)) {
                Set<String> s = d.get(abbr);
                if (!s.contains(w)) s.add(w);
            } else {
                Set<String> s = new HashSet<String>();
                s.add(w);
                d.put(abbr,s);
            }
        }
    }

    /**
     * @param word a string
     * @return true if its abbreviation is unique or false
     */
    public boolean isUnique(String word) {
        // Write your code here
        String a = getAbbr(word);
        if (d.containsKey(a)) {
            Set<String> s = d.get(a);
            if (s.size() == 1 && s.contains(word)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private String getAbbr(String w){
        int len = w.length() - 2;
        if (len <= 0) {
            return w;
        }
        return "" + w.charAt(0) + len + w.charAt(w.length() - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param = obj.isUnique(word);
 */



// version: 高频题班
public class ValidWordAbbr {
    Map<String, Integer> dict = new HashMap<>();
    Map<String, Integer> abbr = new HashMap<>();

    // @param dictionary a list of word
    public ValidWordAbbr(String[] dictionary) {
        // Write your code here
        for (String d : dictionary) {
            dict.put(d, dict.getOrDefault(d, 0) + 1);
            String a = getAbbr(d);
            abbr.put(a, abbr.getOrDefault(a, 0) + 1);
        }
    }
    /**
     * @param word a string
     * @return true if its abbreviation is unique or false
     */
    public boolean isUnique(String word) {
        // Write your code here
        String a = getAbbr(word);
        return dict.get(word) == abbr.get(a);
    }

    String getAbbr(String str) {
        if (str.length() <= 2) {
            return str;
        }
        return "" + str.charAt(0) + (str.length() - 2) + str.charAt(str.length() - 1);
    }
}

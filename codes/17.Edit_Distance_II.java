problem:

Given two strings S and T, determine if they are both one edit distance apart.

code:

mine:
public class Solution {
    /**
     * @param s a string
     * @param t a string
     * @return true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // Write your code here
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        int diff = t.length() - s.length();

        if (diff > 1) {
            return false;
        }
        if (diff == 0) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    cnt++;
                }
            }
            return (cnt == 1);
        }
        if (diff == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    return (s.substring(i).equals(t.substring(i + 1)));
                }
            }
        }
        return true;
    }
}

9 chapter: 
public class Solution {
    /**
     * @param s a string
     * @param t a string
     * @return true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // Write your code here
        int m = s.length();
        int n = t.length();
        if (Math.abs(m - n) > 1)
            return false;

        if (m > n)
            return isOneEditDistance(t, s);
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (m == n) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                // note: if the string difference is one, check the remaining string
                // of index i in s and the remaining string of index i+1 in t 
                return s.substring(i).equals(t.substring(i + 1));
            }
        }
        return m != n;
    }
}

// version: 高频题班
public class Solution {
    /**
     * @param s a string
     * @param t a string
     * @return true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // Write your code here
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        int diff = t.length() - s.length();

        if (diff > 1) {
            return false;
        }
        if (diff == 0) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    cnt++;
                }
            }
            return (cnt == 1);
        }
        if (diff == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (t.charAt(i) != s.charAt(i)) {
                    return (s.substring(i).equals(t.substring(i + 1)));
                }
            }
        }
        return true;
    }
}



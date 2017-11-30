problem:

Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

code: 

mine: 
public class Solution {
    /**
     * @param s a string
     * @return it's index
     */
    public int firstUniqChar(String s) {
        // Write your code here
        // ascII totally 256 numbers
        int[] cnt = new int[256];

        for (char c : s.toCharArray()) {
            cnt[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}

9 chapter: 


public class Solution {
    /**
     * @param s a string
     * @return it's index
     */
    public int firstUniqChar(String s) {
        // Write your code here
        int[] alp = new int[256];
        char[] arr =s.toCharArray();

        for(char c : arr ){
            alp[c]++;
        }

        for(int i = 0; i < arr.length; i++){
            if (alp[arr[i]]==1) return i;
        }

        return -1;
    }
}

// version: 高频题班
public class Solution {
    /**
     * @param s a string
     * @return it's index
     */
    public int firstUniqChar(String s) {
        // Write your code here
        int[] cnt = new int[256];

        for (char c : s.toCharArray()) {
            cnt[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}



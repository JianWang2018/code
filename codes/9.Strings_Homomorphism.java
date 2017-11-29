problem:
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

code: 

mine:

public class Solution {
    /*
     * @param s: a string
     * @param t: a string
     * @return: true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // write your code here
        if (s.length()!=t.length()) 
            return false; 
        
        HashMap <Character, Character> map = new HashMap<Character, Character>(); 
        
        for (int i = 0; i <s.length();i++) {
            if (map.containsKey(s.charAt(i)))
            {
                if (map.get(s.charAt(i)) != t.charAt(i)) 
                return false; 
                
                
            }
            
            else if (map.containsValue(t.charAt(i))) return false;
            
            else map.put(s.charAt(i),t.charAt(i));
        }
        
        return true;
    }
}

9 chapter: 

public class Solution {
    /**
     * @param s a string
     * @param t a string
     * @return true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // Write your code here
        int[] m1 = new int[128];
        int[] m2 = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            int cs = (int) s.charAt(i);
            int ts = (int) t.charAt(i);
            if (m1[cs] == 0 && m2[ts] == 0) {
                m1[cs] = ts;
                m2[ts] = 1;
            } else if (m1[cs] != ts) {
                return false;
            }
        }
        return true;
    }
}

// version: 高频题班
public class Solution {
    /**
     * @param s a string
     * @param t a string
     * @return true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // Write your code here
        int[] map = new int[256];  // ASCII 的范围是0-255
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (map[sc[i]] == 0) {
                map[sc[i]] = tc[i];
            } else {
                if (map[sc[i]] != tc[i]) {
                    return false;
                }
            }
        }

        /*
        ///////////////////////////////假设t的取值只有'a' - 'z' 时做t->s 映射的一种写法   （仅做演示使用）
        int[] map2 = new int[26];
        for (int i = 0; i < t.length(); i++) {
            if (map2[tc[i] - 'a'] == 0) {
                map2[tc[i] - 'a'] = sc[i];
            } else {
                if (map2[tc[i] - 'a'] != sc[i]) {
                    return false;
                }
            }
        }
        */

        int[] map2 = new int[256];
        for (int i = 0; i < t.length(); i++) {
            if (map2[tc[i]] == 0) {
                map2[tc[i]] = sc[i];
            } else {
                if (map2[tc[i]] != sc[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}

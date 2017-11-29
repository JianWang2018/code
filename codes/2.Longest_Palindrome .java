problem:
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

 Notice

Assume the length of given string will not exceed 1010.


code:

mine: use hash table
public class Solution {
    /*
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // write your code here
        if (s.length()==0) return 0; 
        if (s.length()==1) return 1 ; 
        
        HashMap <Character, Integer> dict = new HashMap<Character, Integer>(); 
        
        for ( int i = 0; i<s.length();i++){
            int count = dict.getOrDefault(s.charAt(i),0);
            dict.put(s.charAt(i),count+1);
        }
        int sum =0; 
        int oddCount=0;
        for(char item : dict.keySet())
        { if (dict.get(item)%2==0)
            sum+=dict.get(item);
          else 
            oddCount+=1;
    }
    
    return Math.min(s.length(),s.length()-oddCount+1);
}
}
9chapter: use set

public class Solution {
    /**
     * @param s a string which consists of lowercase or uppercase letters
     * @return the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // Write your code here
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) set.remove(c);
            else set.add(c);
        }

        int remove = set.size();
        if (remove > 0)
            remove -= 1;
        return s.length() - remove;
    }
}



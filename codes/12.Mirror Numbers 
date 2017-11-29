problem:

A mirror number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is mirror. The number is represented as a string.

Have you met this question in a real interview? Yes


code: 

mine:

public class Solution {
    /*
     * @param num: a string
     * @return: true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        // write your code here
        
        //define a hashmap to map number to number, when turn on a 180 rotation 
        
        HashMap <Character,Character> map =new HashMap<Character,Character > ();
    
        
        map.put('0','0');
        
        map.put('1','1');
        map.put('2','#');
        map.put('3','#');
        map.put('4','#');
        map.put('5','#');
        map.put('6','9');
        map.put('7','#');
        map.put('8','8');
        map.put('9','6'); 
        
        int n = num.length(); 
        
        for(int i = 0 ; i<=n/2;i++){
            
            if(map.get(num.charAt(i))!=num.charAt(n-i-1)) return false;
        }
        
        return true;
    }
}

9 chapter: 

public class Solution {
    /**
     * @param num a string
     * @return true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        // Write your code here
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            if (!map.containsKey(num.charAt(left))) {
                return false;
            }
            if (map.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}

// version: 高频题班
public class Solution {
    /**
     * @param num a string
     * @return true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        // Write your code here
        int[] map = new int[256];
        map['0'] = '0';
        map['1'] = '1';
        map['6'] = '9';
        map['8'] = '8';
        map['9'] = '6';
        for (int i = 0; i < num.length(); i++) {
            int j = num.length() - i - 1;
            if (map[num.charAt(i)] != num.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}




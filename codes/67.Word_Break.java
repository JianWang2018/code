/* problem:
Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.

Have you met this question in a real interview? Yes
Example
Given s = "lintcode", dict = ["lint", "code"].

Return true because "lintcode" can be break as "lint code".

key: 1) dynamic programming
2) can first find the max length of the dict word
3) noet that the complex of the hash is the length of the word
*/

// mine :
public class Solution {
    /*
     * @param s: A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        int maxLength = 0;
        for(String word : dict){
            maxLength=Math.max(maxLength, word.length());

        }

        if(s==null || s.length()==0){
            return true;
        }

        boolean [] res= new boolean [s.length()+1];

        res[0]=true;

        for(int i =1 ; i<=s.length();i++){
            res[i]=false;
            for( int lastWordLength=1;lastWordLength<=maxLength&&lastWordLength<=i;lastWordLength++){
                if(!res[i-lastWordLength]) continue;
                String word = s.substring (i-lastWordLength,i);
                if(dict.contains(word)){
                    res[i]=true;
                    break;
                }

            }

        }
        return res[s.length()];
    }
}
int main(){
  for (int i =1 ;i<a.length ;i++ ) {
    cout << i;

  }
}
// 9 chapter:

public class Solution {
  private int getMaxLength(Set<String> dict) {
    int maxLength = 0;
    for (String word : dict) {
      maxLength = Math.max(maxLength, word.length());
    }
    return maxLength;
  }

  public boolean wordBreak(String s, Set<String> dict) {
    if (s == null || s.length() == 0) {
      return true;
    }

    int maxLength = getMaxLength(dict);
    boolean[] canSegment = new boolean[s.length() + 1];

    canSegment[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      canSegment[i] = false;
      for (int lastWordLength = 1;
      lastWordLength <= maxLength && lastWordLength <= i;
      lastWordLength++) {
        if (!canSegment[i - lastWordLength]) {
          continue;
        }
        String word = s.substring(i - lastWordLength, i);
        if (dict.contains(word)) {
          canSegment[i] = true;
          break;
        }
      }
    }

    return canSegment[s.length()];
  }
}

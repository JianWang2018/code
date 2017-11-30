problem:
An abbreviation of a word follows the form . Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Have you met this question in a real interview? Yes
Example
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") // return false
isUnique("cart") // return true
isUnique("cane") // return false
isUnique("make") // return true

code :

mine:
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
/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param = obj.isUnique(word);
 */
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

/*

problem:
Given a string s, cut s into some substrings such that every substring is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Have you met this question in a real interview? Yes
Example
Given s = "aab",

Return 1 since the palindrome partitioning ["aa", "b"] could be produced using 1 cut.

key: 1) interval dynamic programming combined with axis dynamic programing



*/

//mine :
public class Solution {
    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                    = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // preparation
        boolean[][] isPalindrome = getIsPalindrome(s);

        // initialize
        int[] f = new int[s.length() + 1];
        f[0] = 0;

        // main
        for (int i = 1; i <= s.length(); i++) {
            f[i] = Integer.MAX_VALUE; // or f[i] = i
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()] - 1;
    }
}
//9 chapter

:
// version 1
// f[i] 表示前i个字母，最少可以被分割为多少个回文串
// 最后return f[n] - 1
public class Solution {
    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                    = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // preparation
        boolean[][] isPalindrome = getIsPalindrome(s);

        // initialize
        int[] f = new int[s.length() + 1];
        f[0] = 0;

        // main
        for (int i = 1; i <= s.length(); i++) {
            f[i] = Integer.MAX_VALUE; // or f[i] = i
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()] - 1;
    }
}

// version 2
// f[i] 表示前i个字母，最少被切割几次可以切割为都是回文串。
// 最后return f[n]
public class Solution {
    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                    = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // preparation
        boolean[][] isPalindrome = getIsPalindrome(s);

        // initialize
        int[] f = new int[s.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            f[i] = i - 1;
        }

        // main
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }

        return f[s.length()];
    }
}


public class Solution {
    /**
     * @param s a string
     * @return an integer
     */
    private boolean[][] CalcPalin(String s, int n) {
        boolean[][] isPalin = new boolean[n][n];
        int i, j, p;
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                isPalin[i][j] = false;
            }
        }

        for (p = 0; p < n; ++p) {
            i = j = p;
            while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
                isPalin[i][j] = true;
                --i;
                ++j;
            }
        }

        for (p = 0; p < n-1; ++p) {
            i = p;
            j = p + 1;
            while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
                isPalin[i][j] = true;
                --i;
                ++j;
            }
        }

        return isPalin;
    }
    public int minCut(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n+1];
        int i, j, p;
        boolean[][] isPalin = CalcPalin(s, n);

        f[0] = 0;
        for (i=1; i<=n; ++i) {
            f[i] = Integer.MAX_VALUE;
            for (j = 0; j < i; ++j) {
                if (isPalin[j][i-1] && f[j] != Integer.MAX_VALUE && f[j] + 1 < f[i]) {
                    f[i] = f[j] + 1;
                }
            }
        }

        return f[n] - 1;
    }
};

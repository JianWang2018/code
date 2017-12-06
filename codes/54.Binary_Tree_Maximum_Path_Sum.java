problem: 
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Have you met this question in a real interview? Yes
Example
Given the below binary tree:

  1
 / \
2   3
return 6.



key:1)  build a return type, return root to any and any to any. 
2) for root to any, can utilize the maximum path sum from root
3) also need to compare the muximum path sum wih 0;

mine: 


public class Solution {
    /*
     * @param root: The root of binary tree.
     * @return: An integer
     */
class ResultClass{
    int root2Any, any2Any;
    ResultClass(int root2Any, int any2Any){
        this.root2Any=root2Any; 
        this.any2Any=any2Any;
    }
}    
    private ResultClass helper(TreeNode root){
        if (root==null){
            return new ResultClass(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        
        ResultClass left= helper(root.left);
        ResultClass right= helper(root.right);
        
        int root2Any=Math.max(0,Math.max(left.root2Any,right.root2Any))+root.val;
        int any2Any = Math.max(left.any2Any,right.any2Any);
        any2Any=Math.max(any2Any, Math.max(0,left.root2Any)+Math.max(0,right.root2Any)+root.val);
        return new ResultClass(root2Any,any2Any);
        
    }
    public int maxPathSum(TreeNode root) {
        // write your code here
        ResultClass res= helper(root);
        return res.any2Any;
    }
}

9 chapter: 


public class Solution {
    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath; 
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
}


// Version 2:
// SinglePath也定义为，至少包含一个点。
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    private class ResultType {
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer
        int singlePath =
            Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath,
                           Math.max(left.singlePath, 0) + 
                           Math.max(right.singlePath, 0) + root.val);

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }

}


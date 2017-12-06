problem: 

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Have you met this question in a real interview? Yes
Example
Given a binary tree as follow:

  1
 / \ 
2   3
   / \
  4   5
The minimum depth is 2.

key: 1) use traverse or divide and conquer method, both are recursion method
2) if no child node return 1 3) finally return min+1

mine: 

public class Solution {
    /*
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if (root==null) return 0;
        if (root.left==null && root.right==null) return 1;
        if (root.left==null) return minDepth(root.right)+1;
        if (root.right==null) return minDepth(root.left)+1;
        int left, right;
        left=minDepth(root.left);
        right=minDepth(root.right);
        //note the min is not capital case
        return Math.min(left,right)+1;
    }
}
9 chapter: 



public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root){
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
}

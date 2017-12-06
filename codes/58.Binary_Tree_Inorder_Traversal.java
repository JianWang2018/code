/*
problem:

Given a binary tree, return the inorder traversal of its nodes' values.

Have you met this question in a real interview? Yes
Example
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3


return [1,3,2].
key: need to use addAll method to add all the result of a list
*/

//mine: use the recurssion method
public class Solution {
    /*
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<Integer>();

    if (root == null) {
        return result;
    }

    result.addAll(inorderTraversal(root.left));
    result.add(root.val);
    result.addAll(inorderTraversal(root.right));


    return result;
}
}
// 9 chapter: use the non recursion method
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        TreeNode curt = root;
        while (curt != null || !stack.empty()) {
            while (curt != null) {
                stack.add(curt);
                curt = curt.left;
            }
            curt = stack.pop();
            result.add(curt.val);
            curt = curt.right;
        }
        return result;
    }
}

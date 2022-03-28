// 剑指 Offer 26. 树的子结构
/*
  WA
  [10,12,6,8,3,11]
  [10,12,6,8]
  这个例子画出来就明白了
  前序 + null的遍历能唯一确定一棵树
  但是这里要求的是子结构
  这个例子是子树，但是并不是子字符串
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        StringBuilder sbA = new StringBuilder();
        dfs(A, sbA);
        if (B == null) {
            return false;
        }
        StringBuilder sbB = new StringBuilder();
        dfs(B, sbB);
        for (int i = 0; i < sbA.length(); i++) {
            if (sbA.charAt(i) == sbB.charAt(0)) {
                boolean ret = true;
                for (int j = 1; j < sbB.length(); j++) {
                    if (sbA.charAt(i + j) != sbB.charAt(j)) {
                        ret = false;
                        break;
                    }
                }
                if (ret) {
                    return ret;
                }
            }
        }
        return false;
    }

    public void dfs(TreeNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val);
            dfs(root.left, sb);
            dfs(root.right, sb);
        } else {
            sb.append("#");
        }
    }
}
/*
  那么只能同时遍历来判断子结构了！
  为了能快速返回，添加了一个ret变量，然后到处判断
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    boolean ret = false;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        findStart(A, B);
        return ret;
    }

    public void findStart(TreeNode A, TreeNode B) {
        if (A == null) {
            return;
        }
        if (A.val == B.val) {
            ret = true;
            traverse(A, B);
            if (ret) {
                return;
            }
        }

        findStart(A.left, B);
        if (ret) {
            return;
        }
        findStart(A.right, B);
        if (ret) {
            return;
        }
    }

    public void traverse(TreeNode A, TreeNode B) {
        if (B == null) {
            return;
        }
        if (A == null || A.val != B.val) {
            ret = false;
            return;
        }
        traverse(A.left, B.left);
        if (!ret) {
            return;
        }
        traverse(A.right, B.right);
        if (!ret) {
            return;
        }
    }
}

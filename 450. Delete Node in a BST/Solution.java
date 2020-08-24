//450. 删除二叉搜索树中的节点
/*
  根据删除结点可能遇到的三种情况全部穷举，还要另外考虑需要删除的结点是根节点的情况
  虽然很快，但是觉得自己做的非常复杂
  查看题解发现有高端操作，于是乎放弃这种呆呆的做法
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (key == cur.val) {
                if (cur.left == null && cur.right == null) {
                    if (pre == null) {
                        return pre;
                    }
                    if (key < pre.val) {
                        pre.left = null;
                    } else {
                        pre.right = null;
                    }
                } else if (cur.left != null && cur.right != null) {
                    TreeNode newCur = cur.right;
                    TreeNode newCurPre = null;
                    while (newCur.left != null) {
                        newCurPre = newCur;
                        newCur = newCur.left;
                    }
                    if (newCurPre == null) {
                        if (pre == null) {
                            root = newCur;
                        } else {
                            if (key < pre.val) {
                                pre.left = newCur;
                            } else {
                                pre.right = newCur;
                            }
                        }
                        newCur.left = cur.left;
                    } else {
                        newCurPre.left = newCur.right == null ? null : newCur.right;
                        cur.val = newCur.val;
                    }
                } else {
                    if (pre == null) {
                        return cur.left == null ? cur.right : cur.left;
                    }
                    if (key < pre.val) {
                        pre.left = cur.left == null ? cur.right : cur.left;
                    } else {
                        pre.right = cur.left == null ? cur.right : cur.left;
                    }
                }
                return root;
            } else if (key < cur.val) {
                pre = cur;
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        return root;
    }
}
/*
  尝试用自己的思路去写，发现迭代没有递归好表达
  递归的思路是，每次都解决这一层的问题，然后将问题缩小到下一层继续解决
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }

    private int successor(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    private int predecessor(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }
}
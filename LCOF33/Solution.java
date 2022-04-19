// 剑指 Offer 33. 二叉搜索树的后序遍历序列
/*
  最后一个肯定是根节点，然后从前往后遍历，所有小于根节点的，就是左子树，大于根节点的就是右子树
  我们要确保右子树里面，没有小于根节点的，否则返回false
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorder(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int rootVal = postorder[end];
        int split;
        for (split = start; split < end; split++) {
            if (postorder[split] > rootVal) {
                break;
            }
        }
        for (int i = split + 1; i < end; i++) {
            if (postorder[i] < rootVal) {
                return false;
            }
        }
        return verifyPostorder(postorder, start, split - 1) && verifyPostorder(postorder, split, end - 1);
    }
}

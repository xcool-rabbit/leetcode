// 剑指 Offer 37. 序列化二叉树
/*
  同主站297
  执行用时：14 ms, 在所有 Java 提交中击败了79.39%的用户
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder("[");
        dfs(root, sb);
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }

        sb.append(node.val + ",");
        dfs(node.left, sb);
        dfs(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.substring(1, data.length() - 1).split(",");
        Queue<String> queue = new LinkedList(Arrays.asList(datas));
        return dfs2(queue);
    }

    private TreeNode dfs2(Queue<String> data) {
        String val = data.poll();
        if (val.equals("null")) {
            return null;
        }
        TreeNode cur = new TreeNode(Integer.parseInt(val));
        cur.left = dfs2(data);
        cur.right = dfs2(data);
        return cur;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

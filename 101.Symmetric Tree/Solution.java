//101. 对称二叉树
/*
  第一个想到的是层序遍历，然后对每一层鉴定是不是对称
  这种方法最后没有通过，因为深度太深了LTE了
  看着深深的二叉树，忽然想到就通过自己的高超技巧，对左右子树进行镜像的深度遍历
  第二种方法，try！
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> leverList = new ArrayList<>();
        int count = 1;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                queue.offer(null);
                queue.offer(null);
                leverList.add(null);
            } else {
                queue.offer(current.left);
                queue.offer(current.right);
                leverList.add(current.val);
            }
            if (leverList.size() == count) {
                count <<= 1;
                if (isAllNull(leverList)) {
                    return true;
                }
                if (!isReverse(leverList)) {
                    return false;
                }
                leverList.clear();
            }
        }
        return false;
    }

    private boolean isAllNull(List list) {
        for (Object o : list) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isReverse(List<Integer> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            if (list.get(i) == null) {
                if (list.get(list.size() - 1 - i) != null) {
                    return false;
                }
            } else {
                if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new Solution().isSymmetric(root));

    }
}

/*
  本来以为第二种方法要用非递归的DFS，但后来仔细想了一下，层序遍历也可以做，
  只要保证入队之前该节点的左右子树对称即可，就不会出现如果两边的树都往左长看不出来的问题了
  （其实是因为我不太会写非递归的DFS，所以才硬着头皮想了一个层序遍历能做的方法）
  代码里面有一些冗余，但是，我不愿意改了哈哈哈
  执行用时：2 ms 已经战胜 49.24 % 的 java 提交记录
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        Deque<TreeNode> leftQueue = new LinkedList<>();
        Deque<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.offer(left);
        rightQueue.offer(right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            TreeNode leftCurrent = leftQueue.poll();
            TreeNode rightCurrent = rightQueue.poll();
            if (leftCurrent == null || rightCurrent == null) {
                if (leftCurrent != rightCurrent) {
                    return false;
                }
            } else {
                if (leftCurrent.val != rightCurrent.val) {
                    return false;
                }
                if (isChildEqual(leftCurrent, rightCurrent)) {
                    if (leftCurrent.left != null) {
                        leftQueue.offer(leftCurrent.left);
                        rightQueue.offer(rightCurrent.right);
                    }
                    if (leftCurrent.right != null) {
                        leftQueue.offer(leftCurrent.right);
                        rightQueue.offer(rightCurrent.left);
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isChildEqual(TreeNode left, TreeNode right) {
        if (left.left == null || right.right == null) {
            if (left.left != right.right) {
                return false;
            }
        } else {
            if (left.left.val != right.right.val) {
                return false;
            }
        }

        if (left.right == null || right.left == null) {
            if (left.right != right.left) {
                return false;
            }
        } else {
            if (left.right.val != right.left.val) {
                return false;
            }
        }

        return true;
    }
}
/*
  有一点双指针那味了
  在一个循环里同时操作两个指针，镜像对称着遍历，就能判断树是不是镜像对称的了
  细节就是利用上了返回值
  递归永远滴神，迭代就不写了，肯定慢
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
    public boolean isSymmetric(TreeNode root) {
        return root == null || dfs(root.left, root.right);
    }
    
    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
        } else {
            return false;
        }
        
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}

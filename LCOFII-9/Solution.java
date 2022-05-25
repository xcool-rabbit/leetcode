// 剑指 Offer II 009. 乘积小于 K 的子数组
/*
  蠢爆了
  WA
*/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ret = 0;
        for (int offset = 0; offset < nums.length; offset++) {
            int product = 1;
            for (int i = 0; i <= offset; i++) {
                product *= nums[i];
            }
            for (int head = 0; head + offset < nums.length; head++) {
                if (product < k) {
                    System.out.println(product);
                    ret++;
                }
                if (head + offset + 1 < nums.length) {
                    product = product / nums[head] * nums[head + offset + 1];
                }
            }
        }
        return ret;
    }
}
/*
  与其固定窗口然后担心超范围，不如单纯的双层循环，超范围就break
  执行用时：898 ms, 在所有 Java 提交中击败了13.68%的用户
*/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                if (product < k) {
                    ret++;
                } else {
                    break;
                }
            }
        }
        return ret;
    }
}
/*
  可以想到的是每次魂环都从i往后，显然是重复计算了很多
  新的一次循环完全可以基于上一次循环的结果来做
  通过左移或者右移，找到这一次循环的结尾点
  这里写代码的时候要尤其弄清楚自己的变量的含义，这样就不会在+1-1之间迷失自我
  不过从效率上来讲，看来有更好的方法
  当然现在这样已经是很大的进步了
  执行用时：6 ms, 在所有 Java 提交中击败了25.22%的用户
*/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int ret = 0;
        int product = 1;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                product /= nums[i - 1];
            } else {
                product = nums[0];
            }
            j = Math.max(i, j);
            while (j > i && product >= k) {
                product /= nums[j--];
            }
            while (j + 1 < nums.length && product < k) {
                product *= nums[++j];
            }
            // System.out.println("i: " + i);
            // System.out.println("j: " + j);
            if (product < k) {
                ret++;
            }
            ret += (j - i);
            // System.out.println("product: " + product);
            // System.out.println(j - i);
        }
        return ret;
    }
}
/*
  其实上一个答案，有漏洞！
  在LCOFII上能跑通，但是在主站跑不通了！
  首先第一个问题是，j根本不需要--，只有可能是不加
  第二个问题是，k等于0的时候很简单是0
  k等于1的时候，其实也不行的，因为要求小于1
  所以直接if排除掉
  可是我这个现在也是O(n)的算法了，为啥还是没别人快呢
  也就是窗口的策略跟别人不一样罢了，不管了
  执行用时：5 ms, 在所有 Java 提交中击败了42.10%的用户
*/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0 || k == 1) {
            return 0;
        }
        int ret = 0;
        int product = 1;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                product /= nums[i - 1];
            } else {
                product = nums[0];
            }
            // j = Math.max(i, j);
            // while (j > i && product >= k) {
            //     product /= nums[j--];
            // }
            while (j + 1 < nums.length && product < k) {
                product *= nums[++j];
            }
            // System.out.println("i: " + i);
            // System.out.println("j: " + j);
            if (product < k) {
                ret++;
            }
            ret += (j - i);
            // System.out.println("product: " + product);
            // System.out.println(j - i);
        }
        return ret;
    }
}

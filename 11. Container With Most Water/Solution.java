// 11. 盛最多水的容器
/*
  暴力解法，O(N ^ 2)遍历一遍，就知道什么时候盛水最多了
  超出时间限制
*/
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            }
        }
        return max;
    }
}
/*
  a
  执行用时：186 ms, 在所有 Java 提交中击败了5.34%的用户
*/
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if ((height.length - 1 - i) * height[i] <= max) {
                continue;
            }
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            }
        }
        return max;
    }
}
/*
  a
  执行用时：74 ms, 在所有 Java 提交中击败了5.34%的用户
*/
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if ((height.length - 1 - i) * height[i] <= max) {
                continue;
            }
            for (int j = height.length - 1; j > i; j--) {
                if (height[i] * (j - i) <= max) {
                    break;
                }
                max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            }
        }
        return max;
    }
}

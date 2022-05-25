// 剑指 Offer II 004. 只出现一次的数字 
/*
  嗨呀，乌龙了，这题之前做过
  但是LCOFI里面没有关联题，所以这个题算新题
  并且这次重新做知道了一种简单的方法，可以避免数字逻辑推导，直接嗯写
  执行用时：1 ms, 在所有 Java 提交中击败了94.58%的用户
*/
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0;
        int b = 0;
        for (int n : nums) {
            int tmp = ~a & b & n | a & ~b & ~n;
            b = ~a & ~b & n | ~a & b & ~n;
            a = tmp;
        }
        return b;
    }
}

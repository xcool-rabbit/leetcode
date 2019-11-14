//167. 两数之和 II - 输入有序数组
/*
  n方的复杂度，我知道循环的时候可以做一些优化但我觉得，反正再怎么优化也是n方，没什么必要，结果跑了一下，震惊了
  执行用时：280 ms，在图表里面甚至看不到这个用时的人。。。于是决定还是稍微优化一下
*/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] index = {0, 1};
        while (true) {
            if (numbers[index[0]] + numbers[index[1]] == target) {
                return new int[]{index[0] + 1, index[1] + 1};
            }
            if (index[1] + 1 < numbers.length) {
                index[1]++;
            } else {
                index[1] = index[0] + 2;
                index[0]++;
            } 
        }
    }
}
/*
  优化之后还是不是很理想
  执行用时：152 ms 已经战胜 5.06 % 的 java 提交记录
*/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] index = {0, 1};
        while (true) {
            if (numbers[index[0]] + numbers[index[1]] == target) {
                return new int[]{index[0] + 1, index[1] + 1};
            } else if (numbers[index[0]] + numbers[index[1]] < target) {
                if (index[1] + 1 < numbers.length) {
                    index[1]++;
                } else {
                    index[1] = index[0] + 2;
                    index[0]++;
                } 
            } else {
                index[1] = index[0] + 2;
                index[0]++;
            }
        }
    }
}
/*
  看到了上次的成绩，我又沉默了，双指针难道不是这么用的吗？这个题被归为双指针，难道只有这样的效率？
  当然我心里知道可以用map来做。这时我忍不住在网上搜索。果然，还是惊到了。
  根据有序数组这个条件，我们的暴力法就可以直接优化，二分查找！这样效率就是nlogn（这种我就不写了）
  那么问题来了，双指针到底怎么用呢？先把两个指针放到头尾，然后往中间跑。当前的和比预期的小，那么左边往右跑。当前的和比预期大，那么右边往左跑。
  是不是总觉得这种会漏掉一些情况？比如左边往右移，一下子给移多了，又得让右边往左移来调整，实际上是不会的，它的每一种困境都只有一种操作可以解决
  执行用时：1 ms 已经战胜 99.34 % 的 java 提交记录（NB！）
*/
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] index = {0, numbers.length - 1};
        while (true) {
            int sum = numbers[index[0]] + numbers[index[1]];
            if (sum == target) {
                return new int[]{index[0] + 1, index[1] + 1};
            } else if (sum < target) {
                index[0]++;
            } else {
                index[1]--;
            } 
        }
    }
}

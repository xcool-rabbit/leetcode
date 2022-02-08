// 287. 寻找重复数
/*
  二分法试答案
  正常来讲，小于等于1的应该有1个，小于等于n的应该有n个
  但是如果有重复的数，就不会是这样的
  从重复的那个数开始，后面的都会多一个
  就利用这一点，可以二分
  这个方法是O(nlogn)的，不是很快，但是我觉得，值得实现一下，还是很妙的
  执行用时：25 ms, 在所有 Java 提交中击败了38.09%的用户
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
/*
  妙哉妙哉，首先这个题能变成一道判圈题，就非常的妙
  不过题解只是直接告诉你它是一个圈，并没有告诉你为什么能这样想
  有两点，一是index是从0开始的，而value是从1开始的
  value有n + 1个，只能往n个地方跳，必然有两个value是一样的
  一个图中，有两条边同时指向一个节点，那必然是有环的，有可能是自环，也有可能是一个大环
  我另外想到的一个问题就是，假如说，有某些个节点，它自己的位置就是对的，其他节点也不指向它，那怎么办呢
  其实这种情况根本不影响我们的跳跃，因为，index和value都少了一个，他们的相对关系是不变的，还是必然存在环
  这个题的第二个关键在于，判圈算法的运用
  代码里面写了注释，推导了关系
  在第一次快慢指针相遇之后，第二次从头开始，以相同的速度，最终将达到环的入口
  环的入口表明，从头开始能到，环内也能到
  那么它就是我们要找的重复数字
  执行用时：4 ms, 在所有 Java 提交中击败了95.07%的用户
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[nums[0]];
        int fast = nums[slow];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // slow = a + b
        // fast = 2a + 2b = a + b + circle
        // ->
        // slow = a + circle = a + b + a
        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

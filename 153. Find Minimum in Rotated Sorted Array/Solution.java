//153. 寻找旋转排序数组中的最小值
/*
  emmmm一言难尽，不知道这个题想干什么
  O(N)遍历，就能找到最小值
  执行用时：0 ms 已经战胜 100.00 % 的 java 提交记录
*/
class Solution {
    public int findMin(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return nums[i + 1];
            }
        }
        return nums[0];
    }
}
/*
  这是我最初的想法
  二分的策略是比较左和中的值，来判断区间是否递增，最小值出现在非递增区间
  听起来很简单，做起来坑太多了
  二分的时候可能正好把left切到最小值，这样两边都是有序的，就找不出来，需要再加一个if来判断
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[mid];
    }
}
/*
  就在我写题解的时候，我发现这两个if其实很重复
  nums[left] < nums[right]就涵盖了最小值的判断
  于是精简了一下
  另外这里有个小case，可以只有left在变，right保持不变
  因为mid会落在left上，left必须要变，不然可能会无限循环
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[left] <= nums[right]) {
                return nums[left];
            }
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }
}
/*
  官方解法听起来什么右端点怎么怎么样，实际上跟我这个一样，寻找最小值
  感悟：经过几天的沉淀，我觉得我对二分的理解更进一步
  只要是找到了合适的分法，就能成功
*/
/*
  你这泼猴，上述非也，上述非也！
  官方的写法有说法，有大说法，只不过他并没有讲解他为什么要这样写
  为什么要跟nums[right]比较？明明跟nums[left]比较也一样的啊
  你仔细品，这是有选择的
  跟nums[right]比较就可以把那个if省略掉！
  跟nums[right]比较的时候是行为，在处理有序序列的时候一样适用！所以不需要额外再写一个if来处理有序序列了！！！
  这是我自己悟出来的题解！不是稀里糊涂抄的！我觉得我真正掌握了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[right] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}

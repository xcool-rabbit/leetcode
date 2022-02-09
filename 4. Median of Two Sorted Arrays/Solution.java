// 4. 寻找两个正序数组的中位数
/*
  关键思想是，中位数就是第k小的数
  实现起来还是困难重重
  首先讲几个优化的点
  一是在开头就让nums1长，nums2短
  这样中位数一定落在nums1上，后续会省去非常多的判断
  二是关注到处都可能发生的数组越界
  下面讲主要思路
  目标是第k小，那么我可以在两个数组上，分别找第k / 2小的数
  筛去小的那部分，因为我能确保那里面不存在合起来之后的第k小
  遇到一些越界难以处理的情况，可以采用最简单的自增，这样可以省去很多的判断
  但是我一开始做的时候其实就想复杂了，针对所有自增的地方都有更强的优化，下个方法会做讲解
  还有一个优化就是，总个数是偶数，求中位数的时候需要求两个
  而我，先不分奇偶，全给你求出来，最后再根据奇偶状况来返回，代码量会更少一些
  我看我写的挺复杂的，一看题解，它也各种if
  看来这个题就是很麻烦
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 中位数就是第k大的数
        if (nums1.length < nums2.length) { // nums1.length >= nums2.length
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int totalLength = nums1.length + nums2.length;
        int offset1 = 0;
        int offset2 = 0;
        int k = (totalLength + 1) / 2 - offset1 - offset2;
        int mid = 0;
        while (k > 1) {
            // 两个数组各筛k / 2
            mid = k / 2; // index 0
            if (mid - 1 + offset2 < nums2.length) {
                if (nums1[mid - 1 + offset1] <= nums2[mid - 1 + offset2]) {
                    offset1 += mid;
                } else {
                    offset2 += mid;
                }
            } else {
                if (offset2 >= nums2.length || nums1[offset1] <= nums2[offset2]) {
                    offset1++;
                } else {
                    offset2++;
                }
            }
            k = (totalLength + 1) / 2 - offset1 - offset2;
        }
        int ret1 = 0;
        int ret2 = 0;
        if (offset2 >= nums2.length) {
            offset2 = nums2.length;
            offset1 = (totalLength + 1) / 2 - offset2;
            ret1 = nums1[offset1 - 1];
            if (offset1 < nums1.length) {
                ret2 = nums1[offset1];
            }
        } else {
            if (nums1[offset1] <= nums2[offset2]) {
                ret1 = nums1[offset1];
                ret2 = offset1 + 1 < nums1.length ? Math.min(nums1[offset1 + 1], nums2[offset2]) : nums2[offset2];
            } else {
                ret1 = nums2[offset2];
                ret2 = offset2 + 1 < nums2.length ? Math.min(nums1[offset1], nums2[offset2 + 1]) : nums1[offset1];
            }
        }
        if (totalLength % 2 == 1) {
            return ret1;
        } else {
            return (ret1 + ret2) / 2.0;
        }
    }
}
/*
  更快速的解法
  优化了所有自增的地方
  首先要传递一个思想，大家都知道二分快，实际上，不等分也能降低问题规模，总之比遍历要快
  优化发生在nums2的mid越界的时候，这里可以拉一个偏分
  因为已知mid越界，所以就把offset2拉满，然后去相应的调整offset1的大小
  推导思路写在注释里了，核心思想就是，要保证offset1和offset2的增长之和为k，不然就有错过目标值的可能
  理论上更快，但是数据集没有给我发挥的机会，跟上一种的速度一样
  执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 中位数就是第k大的数
        if (nums1.length < nums2.length) { // nums1.length >= nums2.length
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int totalLength = nums1.length + nums2.length;
        int offset1 = 0;
        int offset2 = 0;
        int k = (totalLength + 1) / 2 - offset1 - offset2;
        int mid = 0;
        while (k > 1) {
            // 两个数组各筛k / 2
            mid = k / 2; // index 0
            if (mid - 1 + offset2 < nums2.length) {
                if (nums1[mid - 1 + offset1] <= nums2[mid - 1 + offset2]) {
                    offset1 += mid;
                } else {
                    offset2 += mid;
                }
            } else {
                // offset1 == k - (nums2.length - offset2)
                // offset2 -> offset2 + (nums2.length - offset2) - 1 == (nums2.length - offset2)
                if (offset2 >= nums2.length || nums1[k - (nums2.length - offset2) - 1 + offset1] <= nums2[nums2.length - 1]) {
                    offset1 += k - (nums2.length - offset2);
                } else {
                    offset2 = nums2.length;
                }
            }
            k = (totalLength + 1) / 2 - offset1 - offset2;
        }
        int ret1 = 0;
        int ret2 = 0;
        if (offset2 >= nums2.length) {
            offset2 = nums2.length;
            offset1 = (totalLength + 1) / 2 - offset2;
            ret1 = nums1[offset1 - 1];
            if (offset1 < nums1.length) {
                ret2 = nums1[offset1];
            }
        } else {
            if (nums1[offset1] <= nums2[offset2]) {
                ret1 = nums1[offset1];
                ret2 = offset1 + 1 < nums1.length ? Math.min(nums1[offset1 + 1], nums2[offset2]) : nums2[offset2];
            } else {
                ret1 = nums2[offset2];
                ret2 = offset2 + 1 < nums2.length ? Math.min(nums1[offset1], nums2[offset2 + 1]) : nums1[offset1];
            }
        }
        if (totalLength % 2 == 1) {
            return ret1;
        } else {
            return (ret1 + ret2) / 2.0;
        }
    }
}
/*
  让nums1是最长的方法，比我那个优雅多了
*/
if (nums1.length < nums2.length) {
    return findMedianSortedArrays(nums2, nums1);
}
/*
  还有一种更加降维打击的做法，比题目要求的O(log(m + n))还要快
  可以说是中位数的顶级理解了
  像我这种，不知道如何将中位数转化为更简单的条件的，真是铁憨憨
  只知道遍历
  给两个数组同时做划分
  两个数组的左半边的最大值小于等于两个数组的右半边的最小值
  且左半边的个数等于右半边的个数
  那么它就是中位数的分法
  有两点需要重点理解，中位数的个数限制，让我们只需要在一个数组上操作，另一个数组的划分位置是死的
  后面的实现有两种思路，题解的思路让left和right相遇，让划分尽量向右，我觉得挺难理解的
  我自己想了一种理解，效果是一样的，一物换一物，上面的往右收进来的这个数应该小于下面的往左走漏出来的这个数，这样才能划分的更加符合中位数
  由于这个方法过于强大，我也不实现了，满足题目要求即可[旺柴]
*/

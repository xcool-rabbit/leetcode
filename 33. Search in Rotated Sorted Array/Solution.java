// 33. 搜索旋转排序数组
/*
  首先想到的是找到顶端，然后就变成有序数组的二分了
  想法很简单但是实现起来真是历尽千难万险
  先是找顶端，对于一个峰值在中间的区间来讲，很容易想到一种判断方式
  但是这种方式又有一种mid正好是峰值的情况需要break
  然后这种方式并不能适用于区间已经是有序区间的情况，所以需要在循环开始前在做一个有序的判断
  至此，终于能正确的找到顶点
  第二步同样曲折，有了峰值以后，可以认为划出一个有序数组了，但是这个有序数组的维护可太难了
  首先是越界问题，除了超出数组索引的情况需要处理之外，left和right的大小比较，mid的求解，都要重写
  其次，left和right还有一种特殊的越界判断，指，left一直右移，最后又回到原位的情况，不处理的话会无限循环
  处理方式就是，返回-1
  因为这种情况出现的原因是left已经遍历了一圈了，都没找到想要的值
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[right] > nums[left]) {
                mid = right;
                break;
            }
            if (nums[mid] > nums[mid + 1]) {
                break;
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else {
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        int top = mid;
        left = (top + 1) % nums.length;
        right = top;
        mid = getMid(left, right, nums.length);
        while (!bigger(left, right, top)) {
            if (nums[mid] > target) {
                right = (mid - 1 + nums.length) % nums.length;
                if (right == top) {
                    return -1;
                }
                mid = getMid(left, right, nums.length);
            } else if (nums[mid] == target) {
                return mid;
            } else {
                left = (mid + 1) % nums.length;
                if (left == (top + 1) % nums.length) {
                    return -1;
                }
                mid = getMid(left, right, nums.length);
            }
        }
        return -1;
    }

    public int getMid(int left, int right, int arrayLength) {
        if (left > right) {
            return (left + (right + arrayLength - left) / 2) % arrayLength;
        } else if (left == right) {
            return left;
        } else {
            return left + (right - left) / 2;
        }
    }

    public boolean bigger(int left, int right, int top) {
        if (left > top && right > top) {
            return left > right;
        }
        if (left <= top && right <= top) {
            return left > right;
        }
        return left < right;
    }
}
/*
  上一种解法写起来其实非常的折磨，在我被折磨的过程中，想到了这么一种优化
  既然你都找到顶点了，顶点左右本身就是一次二分，就不用费尽心思维护left和right了
  果然快了很多
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[right] > nums[left]) {
                mid = right;
                break;
            }
            if (nums[mid] > nums[mid + 1]) {
                break;
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else {
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        int top = mid;
        if (target >= nums[0]) {
            left = 0;
            right = top;
        } else {
            left = (top + 1) % nums.length;
            right = nums.length - 1;
        }
        mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }
        return -1;
    }
}
/*
  其实我还瞟了一眼题解，发现没有必要找峰值
  直接在这个“半有序”的数组上就能做二分，我假装没看见得了，我的做法也还ok
*/
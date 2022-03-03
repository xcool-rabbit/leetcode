// 324. 摆动排序 II
/*
  大体思路写在注释里了
  如何能满足题目条件呢，奇数位放小的，偶数位放大的
  这个题就变成了寻找中位数了
  这个我会，快排O(n)就能找到了
  正好我还没有亲自写过，可以借这个机会实现一下
  这只是这个题一小步
  因为里面有重复数据，所以，会出现两个相同的数挨在一起的情况，那么如何避免呢？
  其实挨在一起只会出现在中位数上
  所以第一步，把所有等于中位数的，都抓出来，放到整个数组的中间
  这一步就是很多题解说的three-way-partition
  然后这一步很巧妙，直接插还是避免不了挨在一起的
  比如[3,1,5,5]和[5,5,7,8]
  我就纳闷了，我已经把左边数组的5放到最右头，右边数组的5放到最左头了啊
  为什么还不行呢
  看了好多题解，没有人提到为什么要倒着，我终于领悟了
  先放左再放右的这种插法浪费了一个位置，左右数组的5还离得不够远
  其实不必倒过来，先插右再插左就可以了
  但是，题上要求第一个数小，所以不能这样，只能倒过来
  这才是为什么要倒序
  倒过来，左边先插，左边的开头就是5
  右边也倒过来，右边的结尾是5，这样距离拉的最大
  并不快，还有可以优化的地方
  另外我也没有想到有可以省去额外空间的方法，我没有想到怎么可以原地交换
  执行用时：85 ms, 在所有 Java 提交中击败了6.31%的用户
*/
class Solution {
    public void wiggleSort(int[] nums) {
        // 快排O(n)找到topk（中位数）
        quicksort(nums, 0, nums.length - 1, (nums.length + 1) >> 1);
        // 将所有与中位数相等的，放到中间
        int midIndex = ((nums.length + 1) >> 1) - 1;
        int midNum = nums[midIndex];
        int target = midIndex;
        for (int i = 0; i < target; i++) {
            if (nums[i] == midNum) {
                target--;
                swap(nums, i, target);
            }
        }
        target = midIndex;
        for (int i = nums.length - 1; i > target; i--) {
            if (nums[i] == midNum) {
                target++;
                swap(nums, i, target);
            }
        }
        // 分成左右两个数组，并融合
        int[] ret = new int[nums.length];
        int j = 0;
        for (int i = midIndex; i >= 0; i--) {
            ret[j] = nums[i];
            j += 2;
        }
        j = 1;
        for (int i = nums.length - 1; i > midIndex; i--) {
            ret[j] = nums[i];
            j += 2;
        }
        for (int i = 0; i < ret.length; i++) {
            nums[i] = ret[i];
        }
    }

    public void quicksort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        int pivot = nums[start];
        int split = end;
        for (int i = start + 1; i <= split;) {
            if (nums[i] > pivot) {
                swap(nums, i, split);
                split--;
            } else {
                i++;
            }
        }
        swap(nums, start, split);
        if (split - start + 1 == k) {
            return;
        }
        else if (split - start + 1 > k) {
            quicksort(nums, start, split - 1, k - 1);
        } else {
            quicksort(nums, split + 1, end, k - (split - start + 1));
        }
    }

    public void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}
/*
  O(n)遍历的次数太多了，尝试减少一些遍历
  中位数放到中间这一步，可以跟复制到ret数组合起来
  但是遍历的时候比较讲究，需要注意
  执行用时：62 ms, 在所有 Java 提交中击败了7.93%的用户
*/
class Solution {
    public void wiggleSort(int[] nums) {
        // 快排O(n)找到topk（中位数）
        quicksort(nums, 0, nums.length - 1, (nums.length + 1) >> 1);
        // 将所有与中位数相等的，放到中间
        int midIndex = ((nums.length + 1) >> 1) - 1;
        int midNum = nums[midIndex];
        int[] ret = new int[nums.length];
        int j = nums.length % 2 == 1 ? nums.length - 1 : nums.length - 2;
        for (int i = 0; i < midIndex; i++) {
            if (nums[i] < nums[midIndex]) {
                ret[j] = nums[i];
                j -= 2;
            }
        }
        while (j >= 0) {
            ret[j] = nums[midIndex];
            j -= 2;
        }
        j = 1;
        for (int i = nums.length - 1; i > midIndex; i--) {
            if (nums[i] > nums[midIndex]) {
                ret[j] = nums[i];
                j += 2;
            }
        }
        while (j < ret.length) {
            ret[j] = nums[midIndex];
            j += 2;
        }
        for (int i = 0; i < ret.length; i++) {
            nums[i] = ret[i];
        }
    }

    public void quicksort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        int pivot = nums[start];
        int split = end;
        for (int i = start + 1; i <= split;) {
            if (nums[i] > pivot) {
                swap(nums, i, split);
                split--;
            } else {
                i++;
            }
        }
        swap(nums, start, split);
        if (split - start + 1 == k) {
            return;
        }
        else if (split - start + 1 > k) {
            quicksort(nums, start, split - 1, k);
        } else {
            quicksort(nums, split + 1, end, k - (split - start + 1));
        }
    }

    public void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}
/*
  优化快排，改成了双向奔赴
  单向奔赴虽然看起来很简洁但是有不必要的交换
  我都这样优化了，为什么还不够快
  执行用时：49 ms, 在所有 Java 提交中击败了9.30%的用户
*/
class Solution {
    public void wiggleSort(int[] nums) {
        // 快排O(n)找到topk（中位数）
        quicksort(nums, 0, nums.length - 1, (nums.length + 1) >> 1);

        // 将所有与中位数相等的，放到中间
        int midIndex = ((nums.length + 1) >> 1) - 1;
        int midNum = nums[midIndex];
        int[] ret = new int[nums.length];
        int j = nums.length % 2 == 1 ? nums.length - 1 : nums.length - 2;
        for (int i = 0; i < midIndex; i++) {
            if (nums[i] < nums[midIndex]) {
                ret[j] = nums[i];
                j -= 2;
            }
        }
        while (j >= 0) {
            ret[j] = nums[midIndex];
            j -= 2;
        }
        j = 1;
        for (int i = nums.length - 1; i > midIndex; i--) {
            if (nums[i] > nums[midIndex]) {
                ret[j] = nums[i];
                j += 2;
            }
        }
        while (j < ret.length) {
            ret[j] = nums[midIndex];
            j += 2;
        }
        for (int i = 0; i < ret.length; i++) {
            nums[i] = ret[i];
        }
    }

    public void quicksort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        int pivot = nums[start];
        int l = start;
        int r = end;
        while (l <= r) {
            while (l <= r && nums[l] <= pivot) {
                l++;
            }
            if (l > r) {
                break;
            }
            while (l <= r && nums[r] > pivot) {
                r--;
            }
            if (l > r) {
                break;
            }
            swap(nums, l, r);
        }
        int split = r;
        swap(nums, start, split);
        if (split - start + 1 == k) {
            return;
        }
        else if (split - start + 1 > k) {
            quicksort(nums, start, split - 1, k);
        } else {
            quicksort(nums, split + 1, end, k - (split - start + 1));
        }
    }

    public void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}
/*
  无语了嗷，系统自带的排序比我写的高级O(n)快排快这么多
  另外还有一个奇葩方法，桶排序
  因为数据范围是0 ~ 5000，所以他这个方法能行
  歪门邪道嗷，不学
  执行用时：4 ms, 在所有 Java 提交中击败了39.72%的用户
*/
class Solution {
    public void wiggleSort(int[] nums) {
        // 快排O(n)找到topk（中位数）
        // quicksort(nums, 0, nums.length - 1, (nums.length + 1) >> 1);
        Arrays.sort(nums);

        // 将所有与中位数相等的，放到中间
        int midIndex = ((nums.length + 1) >> 1) - 1;
        int midNum = nums[midIndex];
        int[] ret = new int[nums.length];
        int j = nums.length % 2 == 1 ? nums.length - 1 : nums.length - 2;
        for (int i = 0; i < midIndex; i++) {
            if (nums[i] < nums[midIndex]) {
                ret[j] = nums[i];
                j -= 2;
            }
        }
        while (j >= 0) {
            ret[j] = nums[midIndex];
            j -= 2;
        }
        j = 1;
        for (int i = nums.length - 1; i > midIndex; i--) {
            if (nums[i] > nums[midIndex]) {
                ret[j] = nums[i];
                j += 2;
            }
        }
        while (j < ret.length) {
            ret[j] = nums[midIndex];
            j += 2;
        }
        for (int i = 0; i < ret.length; i++) {
            nums[i] = ret[i];
        }
    }

    public void quicksort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        int pivot = nums[start];
        int split = end;
        for (int i = start + 1; i <= split;) {
            if (nums[i] > pivot) {
                swap(nums, i, split);
                split--;
            } else {
                i++;
            }
        }
        swap(nums, start, split);
        if (split - start + 1 == k) {
            return;
        }
        else if (split - start + 1 > k) {
            quicksort(nums, start, split - 1, k);
        } else {
            quicksort(nums, split + 1, end, k - (split - start + 1));
        }
    }

    public void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}

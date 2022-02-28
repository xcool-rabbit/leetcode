// 912. 排序数组
/*
  这个题会经常的更新，就当练练手，会把所有排序算法都写下来
*/
/*
  快排
  分治法
  递归
  就写最正常最易懂的就行了，别想着什么省一次swap之类的操作，最浅显易懂的最好，想优化要先打好基础
  快排的好处是，好写，空间占用不大；坏处是，不稳定，最差是O(n ^ 2)
  执行用时：5ms,在所有Java提交中击败了92.95%的用户
*/
/*
  因为快速排序算法算是一个国际通用算法，所以可以有一些约定俗成的习惯
  比如，数组切片，常理来讲应该是前闭后开，但是在快排中，前闭后闭显然更方便一些
  再比如，关于变量和函数的命名，pivot是基准，partition是划分左右的函数
*/
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length);
        return nums;
    }

    public void quickSort(int[] nums, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        int split = end - 1;
        int swap;
        // System.out.println("start: " + start + "end: " + end);
        for (int i = start + 1; i <= split && split > start; ) {
            // System.out.println(Arrays.toString(nums));
            if (nums[i] > nums[start]) {
                swap = nums[i];
                nums[i] = nums[split];
                nums[split] = swap;
                split--;
            } else {
                i++;
            }
        }
        swap = nums[start];
        nums[start] = nums[split];
        nums[split] = swap;
        quickSort(nums, start, split);
        quickSort(nums, split + 1, end);
    }
}
/*
  堆排
  原理上来讲跟快排的时间复杂度是一样的，当然堆排的最大优势还是topK问题
  实现起来觉得list好做一点，不用标定堆的长度，可以直接删除
  但是作为一个经典算法，怎么可能不遵循C语言的风格来写呢
  核心功能是sift，一个重新堆化的函数
  第一步是建堆，倒着，从第一个拥有子节点的节点开始，对每一个节点做sift操作，最终能让整个数组呈堆的结构
  这里面需要用到一些常用的树的性质：对于一个完全二叉树来讲，基1的话，index * 2是左孩子，index * 2 + 1是右孩子
  第二步是输出堆顶元素，然后将堆的最后一个提到第一个，再做sift，如此循环，就能依次输出
  堆排好处是，稳定且快；坏处是不好写，空间也不省
  执行用时：7ms,在所有Java提交中击败了57.83%的用户
*/
class Solution {
    public int[] sortArray(int[] nums) {
        int endIndex = nums.length - 1;
        buildHeap(nums, endIndex);
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ret[i] = nums[0];
            nums[0] = nums[endIndex];
            endIndex--;
            sift(nums, 0, endIndex);
        }
        return ret;
    }

    public void buildHeap(int[] nums, int endIndex) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            sift(nums, i, endIndex);
        }
    }

    public void sift(int[] nums, int index, int endIndex) {
        int left, right, min;
        while (true) {
            left = index * 2 + 1;
            if (left > endIndex) {
                break;
            }
            right = index * 2 + 2;
            if (right > endIndex) {
                min = left;
            } else {
                min = nums[left] < nums[right] ? left : right;
            }
            if (nums[index] > nums[min]) {
                swap(nums, index, min);
                index = min;
            } else {
                break;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
/*
  归并！
  集齐三大O(nlogn)的排序写法
  归并的缺点就是额外的空间，好处是好写
  执行用时：11 ms, 在所有 Java 提交中击败了75.68%的用户
*/
class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int[] tmp = new int[right - left + 1];
        int m = left;
        int n = mid + 1;
        int i = 0;
        while (m <= mid && n <= right) {
            if (nums[m] < nums[n]) {
                tmp[i++] = nums[m++];
            } else {
                tmp[i++] = nums[n++];
            }
        }
        while (m <= mid) {
            tmp[i++] = nums[m++];
        }
        while (n <= right) {
            tmp[i++] = nums[n++];
        }
        i = 0;
        m = left;
        while (i < tmp.length) {
            nums[m++] = tmp[i++];
        }
    }
}

// 215. 数组中的第K个最大元素
/*
  堆排
  执行用时：1ms,在所有Java提交中击败了99.47%的用户
*/
class Solution {
    int[] arr;
    int k;
    public int findKthLargest(int[] nums, int k) {
        arr = Arrays.copyOf(nums, k);
        this.k = k;
        buildHeap();
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > arr[0]) {
                arr[0] = nums[i];
                sift(0);
            }
        }
        return arr[0];
    }

    public void buildHeap() {
        for (int i = k / 2 - 1; i >= 0; i--) {
            sift(i);
        }
    }

    public void sift(int index) {
        int left, right, min;
        while (true) {
            left = index * 2 + 1;
            if (left >= k) {
                break;
            }
            right = index * 2 + 2;
            if (right >= k) {
                min = left;
            } else {
                min = arr[left] < arr[right] ? left : right;
            }
            int swap;
            if (arr[index] > arr[min]) {
                swap = arr[index];
                arr[index] = arr[min];
                arr[min] = swap;
                index = min;
            } else {
                break;
            }
        }
    }
}
/*
  Java的PriorityQueue实现了堆
  调用一下
  执行用时：4ms,在所有Java提交中击败了58.96%的用户
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                heap.offer(nums[i]);
            } else if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap.peek();
    }
}

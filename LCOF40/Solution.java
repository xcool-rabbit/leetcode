// 剑指 Offer 40. 最小的k个数
/*
  用快排！比堆排和排序快
  执行用时：4 ms, 在所有 Java 提交中击败了73.63%的用户
*/
class Solution {
    int k;
    public int[] getLeastNumbers(int[] arr, int k) {
        this.k = k;
        quicksort(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    public void quicksort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = arr[start];
        int split = end;
        for (int i = start + 1; i <= split; i++) {
            if (arr[i] >= pivot) {
                swap(arr, i--, split--);
            }
        }
        swap(arr, start, split);
        quicksort(arr, start, split - 1);
        if (split < k) {
            quicksort(arr, split + 1, end);
        }
    }

    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}

// 658. 找到 K 个最接近的元素
/*
  这道题让我注意到了一个细节，二分结束之后，无论找到还是没找到，mid所在的位置都是在target左右
  并且，right是要小于等于left的，否则循环不会结束
  基于这一点，在二分完成后，一左一右，双向往外扩展，直到找够数
  执行用时：3 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] > x) {
                right = mid - 1;
            } else if (arr[mid] == x) {
                break;
            } else {
                left = mid + 1;
            }
        }
        if (arr[mid] == x) {
            left = right = mid;
        } else {
            if (right < 0) {
                right = mid = left;
            } else if (left >= arr.length) {
                left = mid = right;
            } else if (x - arr[right] <= arr[left] - x) {
                left = mid = right;
            } else {
                right = mid = left;
            }
        }
        while (right - left + 1 != k) {
            if (left == 0) {
                right++;
                continue;
            }
            if (right == arr.length - 1) {
                left--;
                continue;
            }
            if (x - arr[left - 1] <= arr[right + 1] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ret = new ArrayList<>(right - left + 1);
        for (int i = left; i <= right; i++) {
            ret.add(arr[i]);
        }
        return ret;
    }
}

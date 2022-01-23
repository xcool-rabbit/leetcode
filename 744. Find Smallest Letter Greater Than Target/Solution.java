// 744. 寻找比目标字母大的最小字母
/*
  踩坑了，没有注意这个letter不是严格递增
  对于严格递增的序列来讲，我这种做法很香的
  利用了一个没什么人提到的特点，二分没有找到的情况下，循环结束后，right < left
  可以轻易的判断target周边的数
  但是这个题不是严格有序，直接gg
*/
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (letters[mid] > target) {
                right = mid - 1;
            } else if (letters[mid] == target) {
                mid++;
                break;
            } else {
                left = mid + 1;
            }
        }
        if (mid >= letters.length) {
            mid %= letters.length;
        }
        return left <= right ? letters[mid] : letters[left % letters.length];
    }
}
/*
  二分，并且还要找出比它大/小的数
  这种二分要注意终止条件，left和right相邻的时候
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[right] <= target ? letters[0] : letters[right];
    }
}

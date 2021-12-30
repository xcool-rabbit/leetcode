// 345. 反转字符串中的元音字母
/*
  找到元音字母，然后交换
  注意这种对撞指针要在两次循环之间判断是否可以终止循环，不然可能会相互错过
  执行用时：2 ms, 在所有 Java 提交中击败了99.19%的用户
*/
class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int front = 0;
        int rear = arr.length - 1;
        while (front < rear) {
            while (front < arr.length && !isVowel(arr[front])) {
                front++;
            }
            if (front >= rear) {
                break;
            }
            while (rear >= 0 && !isVowel(arr[rear])) {
                rear--;
            }
            char tmp = arr[front];
            arr[front] = arr[rear];
            arr[rear] = tmp;
            front++;
            rear--;
        }
        return new String(arr);
    }

    public boolean isVowel(char c) {
        int offset = 'a' - 'A';
        if (c > 'U') {
            c -= offset;
        }
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}

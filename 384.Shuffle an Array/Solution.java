//384. 打乱数组
/*
  需要自己声明一个成员变量存储原数组才能保证可以reset
  洗牌算法就是生成一个随机数然后把数组里面这个数拎出来
  有一个小技巧可以使得拎出来之后不用将后续的元素补到前面去
  就是在数组内进行交换而不是另外开一个数组用来存乱序之后的数组
  还有就是，由于random默认产生的数是从0开始，所以在遍历数组的时候可以倒序
  这样random到的值直接就可以命中
  执行用时：172 ms 已经战胜 87.31 % 的 java 提交记录
*/
import java.util.Arrays;
import java.util.Random;

class Solution {

    private int[] origin;
    private int[] current;
    
    public Solution(int[] nums) {
        origin = Arrays.copyOf(nums, nums.length);
        current = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = current.length - 1; i >= 0; i--) {
            int r = random.nextInt(i + 1);
            int tmp = current[r];
            current[r] = current[i];
            current[i] = tmp;
        }
        return current;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

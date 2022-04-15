// 剑指 Offer 61. 扑克牌中的顺子
/*
  唉这种题就，很烦
  看着很简单，感觉有一万种方法可以实现
  但是很多方法，都有坑
  后面细嗦
  这个方法是，找到一个，再找下一个
  效率是O(n ^ 2)，但是还好，因为n的规模太小了
  然后这里面有很多的分支，都不严谨，但是这个题，比较固定，就，都不是问题
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean isStraight(int[] nums) {
        int min = 14;
        int zeroNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroNum++;
            } else {
                min = Math.min(min, nums[i]);
            }
        }
        if (min == 14) {
            return true;
        }
        int count = 0;
        boolean[] visited = new boolean[nums.length];
        while (count < 5) {
            boolean find = false;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == min && !visited[i]) {
                    count++;
                    visited[i] = true;
                    min++;
                    find = true;
                    break;
                }
            }
            if (!find) {
                if (zeroNum > 0) {
                    count++;
                    zeroNum--;
                    min++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
/*
  我最初想的，排个序，但是遇到了各种各样的问题
  [0,0,2,2,5]，我判断2后面要3，没有3就用0，没有4就用0，然后后面是5，nice
  我的逻辑判断这个为true，这显然不对
  然后我针对重复的做了判断
  结果[1,2,12,0,3]还是不行
  我恼羞成怒，用了上面的方法
  但是当我把上面的写完之后，我心里不服气，反过来用count去辅助判断
  这种情况其实顺子长度为4，我也返回true了
  所以用count数一下长度就行
  然后写成了
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroNum = 5;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                zeroNum = i;
                break;
            }
        }
        if (zeroNum >= 4) {
            return true;
        }
        int last = nums[zeroNum];
        int count = 1;
        for (int i = zeroNum + 1; i < nums.length; i++) {
            if (nums[i] == last + 1) {
                last = nums[i];
                count++;
            } else {
                if (zeroNum > 0) {
                    i--;
                    zeroNum--;
                    last++;
                    count++;
                } else {
                    return false; 
                }
            }
        }
        return count + zeroNum >= 5;
    }
}
/*
  经过耐心且深入的debug，我发现问题其实出在我用0的情况！
  没有控制住i，导致我调用0的时候，i就往后跑，才会闹出一堆乌龙！
  其实根本不需要count
  我晕
  我就经常对这种，看起来简单，但是case又比较多
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroNum = 5;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                zeroNum = i;
                break;
            }
        }
        if (zeroNum >= 4) {
            return true;
        }
        int last = nums[zeroNum];
        int count = 1;
        for (int i = zeroNum + 1; i < nums.length; i++) {
            if (nums[i] == last + 1) {
                last = nums[i];
                count++;
            } else {
                if (zeroNum > 0) {
                    i--;
                    zeroNum--;
                    last++;
                    count++;
                } else {
                    return false; 
                }
            }
        }
        return true;
    }
}

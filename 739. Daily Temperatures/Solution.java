//739. 每日温度
/*
  最简单的方法，双层循环遍历
  执行用时：1019 ms 已经战胜 13.38 % 的 java 提交记录
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int count = 0;
            boolean hasBigger = false;
            for (int j = i + 1; j < T.length; j++) {
                count++;
                if (T[j] > T[i]) {
                    hasBigger = true;
                    break;
                }
            }
            result[i] = hasBigger ? count : 0;
        }
        return result;
    }
}
/*
  上一个方法的问题在于重复
  可以进行一些预处理
  一个数组，除了增就是减，所以我们可以先遍历一遍标定增和减的地方
  对于增的地方来讲，后一天就能看到温度升高
  对于减的地方来讲，可以快速的跳过一片区域，减少遍历
  当然这正是轻微的优化，结果并没有翻天覆地的变化，并且写起来挺复杂的
  先将下降区间存储起来
  增长区间直接标1
  下降区间可以从区间后面开始遍历
  执行用时：981 ms 已经战胜 15.27 % 的 java 提交记录
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Map<Integer, Integer> downInterval = new HashMap<>();
        //数组长度小于2
        boolean down = false;
        int downStart = 0;
        int downEnd = 0;
        for (int i = 1; i < T.length; i++) {
            if (T[i] > T[i - 1]) {
                result[i - 1] = 1;
                if (down) {
                    downEnd = i - 1;
                    downInterval.put(downStart, downEnd);
                    downStart = -1;
                    down = false;
                }
            } else {
                if (!down) {
                    downStart = i - 1;
                }
                down = true;
            }
        }
        // if (downStart != -1) {
        //     for (int i = downStart; i < result.length; i++) {
        //         result[i] = 0;
        //     }
        // }
        for (Map.Entry<Integer, Integer> entry : downInterval.entrySet()) {
            for (int i = entry.getKey(); i < T.length && result[i] != 1; i++) {
                boolean hasBigger = false;
                int j = entry.getValue() + 1;
                int count = j - i;
                while (j < T.length) {
                    if (T[j] > T[i]) {
                        hasBigger = true;
                        break;
                    }
                    count++;
                    j++;
                }
                result[i] = hasBigger ? count : 0;
            }
        }
        return result;
    }
}
/*
  终极解法（并不
  用栈！存储下标！（这可真是太意外了）
  遇到小的就在栈里等着，遇到大的就出栈，并且计算下标的位置差
  执行用时：20 ms 已经战胜 70.13 % 的 java 提交记录
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.empty() && T[stack.peek()] < T[i]) {
                int top = stack.pop();
                ans[top] = i - top;
            }
            stack.push(i);
        }
        return ans;
    }
}
/*
  还有一种更夸张的
  不需要栈的数据结构，倒着，动态规划思想
  倒数第二个可以由倒数第一个的值确定
  就不写了，也是O(N)
*/
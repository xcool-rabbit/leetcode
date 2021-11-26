// 410. 分割数组的最大值
/*
   我气炸了，又是一个蒙答案的题
   离上一道同类题很近（时间上），所以没啥锻炼效果，很重复，一模一样的题
   但是显然从用时上来看，慢了很多，原因是这道题的正统方法是动态规划
   执行用时：2 ms, 在所有 Java 提交中击败了39.06%的用户
   时隔多年，又执行了一遍
   执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for (int n : nums) {
            sum += n;
            max = n > max ? n : max;
        }
        int left = sum % m == 0 ? sum / m : sum / m + 1;
        left = left < max ? max : left;
        int right = sum;
        int mid = (left + right) / 2;
        int answer = mid;
        while (left <= right) {
            if (groupNum(nums, mid) <= m) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return answer;
    }

    public int groupNum(int[] nums, int sum) {
        int num = 0;
        int curSum = 0;
        for (int n : nums) {
            if (curSum + n > sum) {
                num++;
                curSum = n;
            } else {
                curSum += n;
            }
        }
        return num + 1;
    }
}

/*
  这并不是一个能run的答案，但是挺值得纪念的
  用到了动态规划的思想，但是不太对
  贪心策略 + 极致的缓存，最后卡在了解答错误
  错在了我认为分界点只会出现在平均值左右各一位
  举例：[86, 25, 1, 1, 140, 110, 37], m = 4
  这种情况我只会纠结是[86]还是[86, 25]，然而正解是[86, 25, 1, 1]
  我虽然不能完全说清楚为什么，但是能得出一个结论就是：
  按照平均值去划分，只能获得局部的最优解
  对于全局来讲，由于会存在数据分布不均的情况，平均值的思路不可靠，没有任何依据
  
*/
class Solution {
    public int[] restSum;
    public List<Map<Integer, Integer>> splitArrayList;
    public int splitArray(int[] nums, int m) {
        restSum = new int[nums.length];
        calcRestSum(nums);
        splitArrayList = new ArrayList<>(m);
        for (int i = 0; i <= m; i++) {
            splitArrayList.add(new HashMap<>(nums.length));
        }
        return splitArray(nums, 0, m);
    }

    public int splitArray(int[] nums, int start, int m) {
        if (start >= nums.length) {
            return 0;
        }
        if (m == 1) {
            return restSum[start];
        }
        int avg = restSum[start] / m;
        int[] ret = findEnd(nums, start, avg);
        int end = ret[0];
        int sum = ret[1];
        int a;
        if (splitArrayList.get(m - 1).containsKey(end + 2)) {
            a = splitArrayList.get(m - 1).get(end + 2);
        } else {
            a = splitArray(nums, end + 2, m - 1);
        }
        int b;
        if (splitArrayList.get(m - 1).containsKey(end + 1)) {
            b = splitArrayList.get(m - 1).get(end + 1);
        } else {
            b = splitArray(nums, end + 1, m - 1);
        }
        int answer = min(max(sum + nums[end + 1], a), b);
        splitArrayList.get(m).put(start, answer);
        return answer;
    }

    public int[] findEnd(int[] nums, int start, int avg) {
        int sum = 0;
        for (int i = start; i < nums.length; i++) {
            if (sum + nums[i] > avg) {
                return new int[]{i - 1, sum};
            }
            sum += nums[i];
        }
        return new int[]{nums.length - 1, sum};
    }

    public void calcRestSum(int[] nums) {
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            restSum[i] = sum;
        }
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public int min(int a, int b) {
        return a < b ? a : b;
    }
}

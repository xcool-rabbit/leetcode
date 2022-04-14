// 剑指 Offer 45. 把数组排成最小的数
/*
  诶嘿，api调用题
  首先这个题做过
  其次，我之前想复杂了，还得用一套复杂的比较机制，然后还得处理位数
  其实两个前后拼接，比较一下，就可以确定结果了
  （之前用stream做，一如既往的慢）
  执行用时：5 ms, 在所有 Java 提交中击败了46.26%的用户
*/
class Solution {
    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int i : nums) {
            list.add(String.valueOf(i));
        }
        Collections.sort(list, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }
}
/*
  我看优秀的提交用的是String[]，emmm好吧，就这样就会变快
  执行用时：4 ms, 在所有 Java 提交中击败了97.18%的用户
*/
class Solution {
    public String minNumber(int[] nums) {
        String[] list = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(list, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }
}


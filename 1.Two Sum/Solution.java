//1. 两数之和
/*
  最初的想法，一开始想用list将数组先进行一个处理，把所有大于target的值全部过滤掉，只留下小于等于的值。后来发现破坏了元素原本所在的位置信息。再后来，我发现这样过滤不对。并没有说元素非负。
  那就用Map啊！emmm，如果是Map的话，元素又不能重复，哦，gg，老老实实用朴实的方法做吧。
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) {
                map.put(nums[i], i);
            }
        }
        
        // List<Integer> list = new ArrayList<Integer>();
        // for (int i : nums) {
        //     if (i <= target)
        //         list.add(i);
        // }
        
        for (Integer i : map.keyset) {
            
        }
        
        // for (int i = 0; i < list.size(); i++) {
        //     for (int j = i + 1; j < list.size(); j++) {
        //         if (list.get(i) + list.get(j) == target) {
        //             return new int[]{i, j};
        //         }
        //     }
        // }
        
        return null;
    }
}
/*
  最朴实的当然是两层循环了！
  执行用时：30 ms 已经战胜 42.82 % 的 java 提交记录
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        
        return null;
    }
}
/*
  表现不尽如人意，去网上找找。。。
  emmm，我好像跟大神之间差一点儿操作。
  同样是利用HashMap，先查询keySet里是否有自己，没有的话存储target-自己，有的话答案就出来了。
  这样很巧妙的解决了Map中Key不能重复的问题。
  执行用时：8 ms 已经战胜 82.31 % 的 java 提交记录
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Integer tmp = null;
        
        for (int i = 0; i < nums.length; i++) {
            if ((tmp = map.get(nums[i])) != null)
                return new int[]{(int)tmp, i};
            else
                map.put(target - nums[i], i);
        }
        
        return null;
    }
}

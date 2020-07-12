//454. 四数相加 II
/*
  四层嵌套循环
  可想而知过不了
  46 / 48 个通过测试用例
*/
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        
        for (int a : A) {
            for (int b : B) {
                for (int c : C) {
                    for (int d : D) {
                        if (a + b + c + d == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        
        return count;
    }
}
/*
  减少了一层维度，拿出一层放到map里，直接查找
  注意是map，因为数组里面可能有重复的元素
  47 / 48 个通过测试用例
*/
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> mapD = new HashMap<>(D.length);
        for (int d : D) {
            Integer times = mapD.get(d);
            if (times == null) {
                mapD.put(d, 1);
            } else {
                mapD.put(d, times + 1);
            }
        }
        int count = 0;
        
        for (int a : A) {
            for (int b : B) {
                for (int c : C) {
                    int sum = a + b + c;
                    Integer times = mapD.get(-sum);
                    if (times != null) {
                        count += times;
                    }
                }
            }
        }
        
        return count;
    }
}
/*
  两两分组是可以的，只需计算A + B和C + D就可以了，不需要计算其他组合
  运用了Map，史诗级领先
  执行用时：72 ms 已经战胜 92.11 % 的 java 提交记录
*/
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> mapAB = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                Integer times = mapAB.get(sum);
                if (times == null) {
                    mapAB.put(sum, 1);
                } else {
                    mapAB.put(sum, times + 1);
                }
            }
        }
        for (int c : C) {
            for (int d : D) {
                int sum = c + d;
                Integer times = mapAB.get(-sum);
                if (times != null) {
                    count += times;
                }
            }
        }
        
        return count;
    }
}

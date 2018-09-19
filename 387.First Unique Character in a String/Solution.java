//387. 字符串中的第一个唯一字符
/*
  最粗暴的方法，遍历就完事儿了
  执行用时：61 ms 已经战胜 40.31 % 的 java 提交记录
*/
class Solution {
    public int firstUniqChar(String s) {
        char[] a = s.toCharArray();
        int i, j;
        
        for (i = 0; i < a.length; i++) {
            for (j = 0; j < a.length; j++) {
                if (i != j) {
                    if (a[i] == a[j])
                        break;
                }
            }
            if (j >= a.length)
                return i;
        }
        
        return -1;
    }
}
/*
  对整个字符串进行预处理，将每个字符放到map里，key为字符，value为索引，若遇到重复的，则将value置为最大（或者-1，一个意思）。
  最终将map的value提炼出来，最小的value就是所求索引。
  但是还是如此的慢。。。不解
  执行用时：74 ms 已经战胜 31.74 % 的 java 提交记录
*/
//这是将value置为最大
class Solution {
    public int firstUniqChar(String s) {
        char[] a = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i]))
                map.put(a[i], Integer.MAX_VALUE);
            else
                map.put(a[i], i);
        }
        
        List<Integer> list = new ArrayList<Integer>(map.values());
        int min = Integer.MAX_VALUE;
        for (Integer i : list) {
            if ((int)i < min)
                min = i;
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
//这是将value置为-1
class Solution {
    public int firstUniqChar(String s) {
        char[] a = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i]))
                map.put(a[i], -1);
            else
                map.put(a[i], i);
        }
        
        List<Integer> list = new ArrayList<Integer>(map.values());
        int min = -1;
        boolean exist = false;
        for (Integer i : list) {
            if ((int)i != -1) {
                if (min == -1)
                    min = (int)i;
                else if ((int)i < min) {
                    min = (int)i;
                }
            }
        }
        
        return min;
    }
}
/*
  这是最优秀的解法。
  思想跟上一个方法一样，但是做了结构性的优化：
  因为字符的数量有限，只有26个，并且是确定的，所以不必用map这样复杂的结构，可以只用布尔数组来进行标记。
  用-2表示该字母从未出现，-1表示该字母已重复，从0开始就是每个字母在字符串中的索引。
  执行用时：22 ms 已经战胜 82.45 % 的 java 提交记录
*/
class Solution {
    public int firstUniqChar(String s) {
        char[] c = s.toCharArray();
        int[] a = new int[26];
        Arrays.fill(a, -2);
        
        for (int i = 0; i < c.length; i++) {
            if (a[c[i] - 'a'] >= 0) {
                a[c[i] - 'a'] = -1;
            }
            else if (a[c[i] - 'a'] == -2) {
                a[c[i] - 'a'] = i;
            }
        }
        
        int min = -1;
        for (int i : a) {
            if (i != -1 && i != -2) {
                if (min == -1 || i < min)
                    min = i;
            }
        }
        
        return min;
    }
}

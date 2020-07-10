//49. 字母异位词分组
/*
  暴力解法
  给每个字符串都建立一个int数组，存储每一个字符各有多少个
  然后根据这个int数组去判断是不是字母异位词
  一样的放到一个列表里
  我寻思再快也不过如此
  执行用时：967 ms （根本看不到超越了多少人，总之很慢就是了）
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<int[]> sampleList = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();
        for (String s : strs) {
            int index = findSample(sampleList, s);
            if (index == -1) {
                List<String> tmp = new ArrayList<>();
                tmp.add(s);
                ans.add(tmp);
            } else {
                ans.get(index).add(s);
            }
        }
        return ans;
    }
    
    private int findSample(List<int[]> sampleList, String s) {
        int[] a = str2IntArray(s);
        for (int i = 0; i < sampleList.size(); i++) {
            if (isArraySame(sampleList.get(i), a)) {
                return i;
            }
        }
        sampleList.add(a);
        return -1;
    }
    
    private int[] str2IntArray(String s) {
        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
        }
        return a;
    }
    
    private boolean isArraySame(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
/*
  说了一万遍，双层循环的题考虑hash
  空间换时间是真的香
  有三种方法可以将该问题转化为Map
  1. 对字符串排序
  2. 自己定义一种字符串的格式，比如"#1#2#0#..."用来表示每个字符出现的次数
     为什么要这样呢？这不是跟我用int数组差不多？
     因为这样可以用在Map里面当key
  3. 26个字母用26个质数来表示，字符串用乘积来表示，同样具有唯一性，缺点在于字符串长点的话可能会翻车
  这里采用的是第二种，理论上的话应该比排序要快的
  从我程序的角度来看实在搞不懂开销慢在哪里了
  要真的说开销的话，可能是我这个字符串不好算hash？算hash的开销比字符串排序还大（同时字符串还很短）
  我做字符串排序必能过（就不做了）
  执行用时：18 ms 已经战胜 25.40 % 的 java 提交记录
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String key = convert(s);
            List<String> value = map.get(key);
            if (value == null) {
                List<String> tmp = new ArrayList<>();
                tmp.add(s);
                map.put(key, tmp);
            } else {
                value.add(s);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> l : map.values()) {
            ans.add(l);
        }
        return ans;
    }
    
    private String convert(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int n : count) {
            sb.append('#');
            sb.append(n);
        }
        return sb.toString();
    }
}

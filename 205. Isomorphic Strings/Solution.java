//205. 同构字符串
/*
  建立一个映射，但要保证value的唯一性，不能出现两个字符映射同一个的情况
  执行用时：19 ms 已经战胜 18.44 % 的 java 提交记录
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Map<Character, Character> map = new HashMap<>(sc.length);
        for (int i = 0; i < sc.length; i++) {
            Character tmp = map.get(sc[i]);
            if (tmp == null) {
                if (map.containsValue(tc[i])) {
                    return false;
                } else {
                    map.put(sc[i], tc[i]);
                }
            } else {
                if (tmp != tc[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
/*
  上面那种做法性能不理想的原因在于，判断value重复的开销是O(n)
  众所周知，map的key是唯一的，但是value不是唯一的
  我们可以利用key的唯一性，反函数思想
  把那两个字符串交换一下，再验证一遍，也比保证value唯一要快
  执行用时：11 ms 已经战胜 62.72 % 的 java 提交记录
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Map<Character, Character> map = new HashMap<>(sc.length);
        for (int i = 0; i < sc.length; i++) {
            Character tmp = map.get(sc[i]);
            if (tmp == null) {
                map.put(sc[i], tc[i]);
            } else {
                if (tmp != tc[i]) {
                    return false;
                }
            }
        }
        sc = t.toCharArray();
        tc = s.toCharArray();
        map = new HashMap<>(sc.length);
        for (int i = 0; i < sc.length; i++) {
            Character tmp = map.get(sc[i]);
            if (tmp == null) {
                map.put(sc[i], tc[i]);
            } else {
                if (tmp != tc[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}

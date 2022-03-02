// 179. 最大数
/*
  大体上用的插入排序，把能插的位置都插一遍，谁大就是谁，O(n ^ 2)
  但是在判断大小这里，我用的TreeMap，还有O(logn)的开销
  所以总体的算法复杂度是O(nnlogn)
  确实，实际情况跟理论上一样慢
  优化：没有必要全插，通过比较开头，可以省去很大一部分的插入，理论上来讲加速了10倍
  有的杠精就要说了，那你比较两位，岂不是能快100倍？emmm理论上来讲是这样的，但是写起来太麻烦了，你咋不去比较三位呢？
  所以后面再多位数的比较我其实都交给TreeMap去做了
  为什么我认为直接排序不行？或者说采用一些策略将位数对齐也不行
  [432, 43243]
  只有在知道432后面是啥的情况下，才能判断二者的大小
  无论是将432补0还是补4，都无法得到绝对的正确答案
  注：这里用#将list和sb维护的插入点隔开了
  当然你也可以用List之类的来同时存储两个不相干的Integer
  执行用时：20 ms, 在所有 Java 提交中击败了5.31%的用户
*/
class Solution {
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            String s = String.valueOf(num);
            if (list.size() == 0) {
                list.add(s);
                sb.append(s);
                continue;
            }
            String[] index = find(list, sb, s).split("#");
            list.add(Integer.valueOf(index[0]), s);
            sb.insert(Integer.valueOf(index[1]), s);
        }
        return sb.toString().startsWith("0") ? "0" : sb.toString();
    }

    public String find(List<String> list, StringBuilder sb, String s) {
        int i;
        int curIndex = 0;
        TreeMap<String, String> compare = new TreeMap<>(Comparator.reverseOrder());
        for (i = 0; i < list.size(); i++) {
            if (s.charAt(0) > list.get(i).charAt(0)) {
                break;
            } else if (s.charAt(0) == list.get(i).charAt(0)) {
                sb.insert(curIndex, s);
                compare.put(sb.toString(), i + "#" + curIndex);
                sb.delete(curIndex, curIndex + s.length());
            }
            curIndex += list.get(i).length();
        }
        if (compare.size() == 0) {
            return i + "#" + curIndex;
        } else {
            sb.insert(curIndex, s);
            compare.put(sb.toString(), i + "#" + curIndex);
            sb.delete(curIndex, curIndex + s.length());
            return compare.firstEntry().getValue();
        }
    }
}
/*
  嗨呀，题解豁然开朗
  还是排序，但是排序的依据不太一样
  比较任意两个数的前后关系，只要比较x拼接y和y拼接x的大小即可
  因为，这两个数放到最后的答案里，肯定是有前后关系的
  我们从他们两个的局部关系来看，他们中间应该是隔着若干的0的
  可是无论他们之间隔着几个0，我的大小判断都是对的
  那么说明，我排序的结果，交换任意两个数，都会变小，那么我就是最大的
  其实私以为这样的解释不够严谨，因为，有可能最大值需要三个数互相交换才能得到
  题解有严谨的证明，太长了，就，算了
  尝试说服自己的话就是，这个排序思路确实解决了我的问题，至于严谨性，我自己想不明白
  执行用时：4 ms, 在所有 Java 提交中击败了86.75%的用户
*/
class Solution {
    public String largestNumber(int[] nums) {
        StringBuilder ret = new StringBuilder();
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (s1, s2) -> ((s2 + s1).compareTo(s1 + s2)));
        for (String s : strings) {
            ret.append(s);
        }
        return ret.toString().startsWith("0") ? "0" : ret.toString();
    }
}

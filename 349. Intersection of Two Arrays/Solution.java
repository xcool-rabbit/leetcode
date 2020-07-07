//349. 两个数组的交集
/*
  直觉是双层循环慢慢找
  Set这个数据结构，虽然构建的时候有些开销，但是在判断是否包含的时候，效率奇高，所以还是Set更快
  执行用时：3 ms 已经战胜 97.91 % 的 java 提交记录
*/
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int n : nums1) {
            set1.add(n);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int n : nums2) {
            set2.add(n);
        }
        Set<Integer> ansSet = new HashSet<>();
        for (Integer n : set1) {
            if (set2.contains(n)) {
                ansSet.add(n);
            }
        }
        int[] ans = new int[ansSet.size()];
        int index = 0;
        for (Integer n : ansSet) {
            ans[index++] = n;
        }
        return ans;
    }
    
//     private boolean contains(int[] a, int num) {
//         for (int n : a) {
//             if (n == num) {
//                 return true;
//             }
//         }
//         return false;
//     }
}

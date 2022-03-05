// 剑指 Offer 51. 数组中的逆序对
/*
  先做的315题，再来做这个
  所以用的是315题的方法，不仅知道总数，还知道每个数能组成的逆序对的数量
  执行用时：46 ms, 在所有 Java 提交中击败了7.39%的用户
*/
class Solution {
    int[] c;
    public int reversePairs(int[] nums) {
        int ret = 0;
        Set<Integer> set = new HashSet<>(nums.length);
        Map<Integer, Integer> indexCache = new HashMap<>(set.size());
        for (int n : nums) {
            set.add(n);
        }
        int[] a = new int[set.size()];
        int index = 0;
        for (int n : set) {
            a[index++] = n;
        }
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            indexCache.put(a[i], i + 1);
        }

        c = new int[a.length + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            index = indexCache.get(nums[i]);
            ret += getSum(index - 1);
            update(index, 1);
        }
        return ret;
    }

    public int lowBit(int index) {
        return index & (-index);
    }

    public int getSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += c[index];
            index -= lowBit(index);
        }
        return sum;
    }

    public void update(int index, int delta) {
        while (index < c.length) {
            c[index] += delta;
            index += lowBit(index);
        }
    }
}
/*
  归并！
  所有的题解都是在教我归并怎么写，没有一个人告诉我为什么要用归并以及怎么用归并解决这个问题
  首先讲一下归并是怎么算的
  两个初具规模的数组，归并的时候，只要有左边大于右边的情况，就说明，右边这几个小子，统统被左边这个数，逆序了！
  所以在合并的过程当中，就能统计出来有多少逆序
  下一个问题很关键，并且也没有人讲
  你合并完了之后，是个有序数组了啊，下一次合并，算的逆序，对吗？
  没有问题！
  因为合并完之后，左边这些，还是永远都在右边那些的左边，相对位置没有变！
  这才是能用归并的依据！
  写法的话，先写一个归并排序，然后在合并的时候，改造一下
  每当左边的指针右移的时候，就看看右边的指针已经移动了多少，这个多少，就表明，左边这个数大于右边几个，就是逆序
  从时间上来看，上面那个没有慢太多，应该是用归并的人太多了，所以显得树状数组的成绩不好
  执行用时：32 ms, 在所有 Java 提交中击败了60.83%的用户
*/
class Solution {
    int ret;
    public int reversePairs(int[] nums) {
        ret = 0;
        mergeSort(nums, 0, nums.length - 1);
        return ret;
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >>> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int[] tmp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
                ret += j - mid - 1;
            } else {
                tmp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
            ret += j - mid - 1;
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        i = left;
        k = 0;
        while (i <= right) {
            nums[i++] = tmp[k++];
        }
    }
}
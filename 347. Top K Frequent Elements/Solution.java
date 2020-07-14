//347. 前 K 个高频元素
/*
  统计频率然后根据频率排序
  这里用到了根据HashMap的value排序
  先将map转换为entryList
  然后调用Collections.sort方法，并自己实现Comparator接口
  对于topK问题来讲这并不是最理想的方法，但是已经挺快的了（手动狗头
  执行用时：17 ms 已经战胜 69.53 % 的 java 提交记录
*/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            Integer times = map.get(n);
            if (times == null) {
                map.put(n, 1);
            } else {
                map.put(n, times + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        int[] ans = new int[k];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = entryList.get(i).getKey();
        }
        return ans;
    }
}
/*
  看了一下题解才发现，Java是有PriorityQueue实现堆的结构的
  那么topK问题就可以更快了
  执行用时：14 ms 已经战胜 90.78 % 的 java 提交记录
*/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            Integer times = map.get(n);
            if (times == null) {
                map.put(n, 1);
            } else {
                map.put(n, times + 1);
            }
        }
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.offer(entry);
            } else {
                if (queue.peek().getValue() < entry.getValue()) {
                    queue.poll();
                    queue.offer(entry);
                }
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = queue.poll().getKey();
        }
        return ans;
    }
}

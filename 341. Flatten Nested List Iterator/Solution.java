// 341. 扁平化嵌套列表迭代器
/*
  把嵌套列表转换成正常的列表，然后再慢慢迭代
  缺点显而易见，需要先遍历一遍
  那我为什么要这样做呢，因为我写不出来那种能跟着递归走的迭代器啊
  执行用时：4 ms, 在所有 Java 提交中击败了43.74%的用户
*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    List<Integer> list;
    Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        travel(nestedList);
        it = list.iterator();
    }

    public void travel(List<NestedInteger> nestedList) {
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                list.add(n.getInteger());
            } else {
                travel(n.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
/*
  真的就写不出来了吗？栈啊！栈永远是递归的好朋友！
  栈里面存放迭代器
  这个想法有点反人类（至少是反我的直觉）
  需要注意的点：对于只有一个数字的NestedInteger，需要造一个List，然后把Iterator放到栈里
  因为执行完hasNext()之后，在执行next()的时候，一定要有一个Integer返回，所以，在hasNext()里，要一直循环到把只含有一个Integer的Iterator喂到栈顶，才可以退出hasNext()函数
  最恼火的是！我顶着反直觉的思想，写了好久，最后发现并没有变快！
  这个应该跟测试用例有关系，从原理上来讲，这个方法肯定是更快的。可能开销都在造List和生成Iterator那里了
  执行用时：4 ms, 在所有 Java 提交中击败了43.74%的用户
*/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> top = stack.peek();
            if (top.hasNext()) {
                NestedInteger next = top.next();
                if (next.isInteger()) {
                    List<NestedInteger> list = new ArrayList<>();
                    list.add(next);
                    stack.push(list.iterator());
                    return true;
                } else { 
                    stack.push(next.getList().iterator());
                }
            } else {
                stack.pop();
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
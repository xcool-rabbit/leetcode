// 1600. 皇位继承顺序
/*
  我大受震撼，并且气急败坏
  这道题显然就是一个树的实现
  实现一个节点类组成一个数据结构是树的常规操作
  但是这个题这样做时间是不够的
  由于他有一个特性，就是所有节点的名字是独一无二的
  所以可以投机取巧一下，不一定要按照树的结构存储
  就比如说一个，完全二叉树，可以用数组存储，不一定要用链表的方式存储
  话说回来，这个独一无二的名字，怎么投机取巧呢
  用HashMap
  常规的树的结构，想找地方birth需要dfs
  然而可以通过名字来区分节点的话，一定程度上不再依赖树的结构
  可以用HashMap，名字当key，来存储，value是孩子的名字列表
  这样可以快速的找到父亲然后生孩子
  先放一个未通过的版本，因为思路也不错，包括一些优化的细节
  增加了一个set用来存储所有孩子的名字，如果要找的就是其中的孩子，那么可以快速BFS，不用再慢慢DFS了
  另外说一个，好像也许可能，LinkedHashMap有bug
  这个List和Set的组合，可以融合成一个HashMap
  但是这个又需要有序，于是就使用了LinkedHashMap，但是效果不是很好，有一个用例一直过不去
  所以我怀疑LinkedHashMap有问题，抑或是我理解有问题，没有正确使用
*/
class ThroneInheritance {
    class TreeNode {
        String name;
        List<TreeNode> children;
        Set<String> childNameSet;

        TreeNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
            this.childNameSet = new HashSet<>();
        }
    }

    TreeNode root;
    Set<String> deathList;
    List<String> inheritanceOrder;

    public ThroneInheritance(String kingName) {
        root = new TreeNode(kingName);
        deathList = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        TreeNode parent = findNode(root, parentName);
        parent.children.add(new TreeNode(childName));
        parent.childNameSet.add(childName);
    }

    private TreeNode findNode(TreeNode n, String name) {
        if (n.name.equals(name)) {
            return n;
        }
        if (n.children.size() == 0) {
            return null;
        }
        if (n.childNameSet.contains(name)) {
            for (TreeNode node : n.children) {
                if (node.name.equals(name)) {
                    return node;
                }
            }
        }
        for (TreeNode node : n.children){
            TreeNode ret = findNode(node, name);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }
    
    public void death(String name) {
        deathList.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        inheritanceOrder = new LinkedList<>();
        dfs(root);
        return inheritanceOrder;
    }

    private void dfs(TreeNode n) {
        if (!deathList.contains(n.name)) {
            inheritanceOrder.add(n.name);
        }
        if (n.children.size() == 0) {
            return;
        }
        for (TreeNode node : n.children) {
            dfs(node);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
/*
  虽然通过了，但是不是很理想
  对比了一下题解，没有孩子的人，就不开一个key了，能节省时间
  执行用时：387 ms, 在所有 Java 提交中击败了12%的用户
*/
class ThroneInheritance {
    String kingName;
    Map<String, List<String>> inheritanceMap;
    Set<String> deathList;
    List<String> inheritanceOrder;

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        inheritanceMap = new HashMap<>();
        inheritanceMap.put(kingName, new ArrayList<>());
        deathList = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        inheritanceMap.get(parentName).add(childName);
        inheritanceMap.put(childName, new ArrayList<>());
    }

    public void death(String name) {
        deathList.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        inheritanceOrder = new LinkedList<>();
        dfs(kingName);
        return inheritanceOrder;
    }

    private void dfs(String name) {
        if (!deathList.contains(name)) {
            inheritanceOrder.add(name);
        }
        if (inheritanceMap.get(name).size() == 0) {
            return;
        }
        for (String s : inheritanceMap.get(name)) {
            dfs(s);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
/*
  实现了上面说的那个改动
  执行用时：316 ms, 在所有 Java 提交中击败了77.40%的用户
*/
class ThroneInheritance {
    String kingName;
    Map<String, List<String>> inheritanceMap;
    Set<String> deathList;
    List<String> inheritanceOrder;

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        inheritanceMap = new HashMap<>();
        inheritanceMap.put(kingName, new ArrayList<>());
        deathList = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        if (!inheritanceMap.keySet().contains(parentName)) {
            inheritanceMap.put(parentName, new ArrayList<>());
        }
        inheritanceMap.get(parentName).add(childName);
    }

    public void death(String name) {
        deathList.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        inheritanceOrder = new LinkedList<>();
        dfs(kingName);
        return inheritanceOrder;
    }

    private void dfs(String name) {
        if (!deathList.contains(name)) {
            inheritanceOrder.add(name);
        }
        if (!inheritanceMap.keySet().contains(name)) {
            return;
        }
        for (String s : inheritanceMap.get(name)) {
            dfs(s);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
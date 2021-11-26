// 789. 逃脱阻碍者
/*
  这道题很有意思，看似复杂，在表格里面追逐，实际上只要掌握一点
  阻碍者只要能先于你到达终点，就一定能阻碍你，根本不需要关心他到底是怎么走的，在哪里能阻碍你，你要怎么躲开
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int myDistance = calcDistance(new int[]{0, 0}, target);
        int minGhostDistance = Integer.MAX_VALUE;
        for (int[] ghost : ghosts) {
            int curGhostDistance = calcDistance(ghost, target);
            minGhostDistance = minGhostDistance < curGhostDistance ? minGhostDistance : curGhostDistance;
        }
        return myDistance < minGhostDistance;
    }

    public int calcDistance(int[] src, int[] dst) {
        return Math.abs(src[0] - dst[0]) + Math.abs(src[1] - dst[1]);
    }
}

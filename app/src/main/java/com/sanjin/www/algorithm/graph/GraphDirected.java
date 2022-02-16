package com.sanjin.www.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2021/3/12
 * desc: 有向图
 * note:
 */
public class GraphDirected {

    int v; // 顶点数
    LinkedList<Integer>[] adj;// 邻接表

    public GraphDirected(int v) {
        this.v = v;
        adj = new LinkedList[v];
        // 初始化邻接表
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // 有向图添加新边，邻接表只需要添加一次，即只需要给一个顶点的邻接容器添加它的邻接顶点即可表达完整的有向图
    public void addEdge(int s, int t) { // s先于t，边s->t
        adj[s].add(t);
    }

    /**
     * 拓扑排序：kahn算法
     */
    public int[] topoSortByKahn() {
        // 1. 创建并初始化记录当前所有顶点入度情况的容器
        int[] inDegree = new int[v]; // 记录入度的容器
        for (int i = 0; i < v; i++) { // 遍历所有顶点
            // 遍历某个顶点所有的边, 将顶点i所有的边对应的顶点入度加1
            for (int j = 0; j < adj[i].size(); j++) {
                int t = adj[i].get(j); // 顶点i的第j个边对应的顶点是t
                inDegree[t]++;
            }
        }

        // 2. 创建并初始化记录当前入度为0的顶点的队列容器
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 3. 将入度为0的顶点从队列中取出来，并打印该顶点，然后将该顶点连接边对应的顶点入度减去1，并将减去1后入度为0的顶点放入队列中
        ArrayList<Integer> results = new ArrayList<>();
        while (!queue.isEmpty()) {
            int s = queue.remove(); // 取出队列中入度为0的顶点
            results.add(s);
            for (int i = 0; i < adj[v].size(); i++) {
                int t = adj[s].get(i);
                inDegree[t]--;
                if (inDegree[t] == 0) {
                    queue.add(t);
                }
            }
        }

        // 检查图是否有环
        // 数量不相等，则说明存在环
        if (results.size() != v) {
            return new int[0];
        }

        // 将results转换为数组并返回Kahn算法的拓扑排序结果
        int[] array = new int[v];
        int k = 0;
        for (int i : results) {
            array[k++] = i;
        }
        return array;
    }

    /**
     * 拓扑排序 -- 深度优先遍历实现
     */
    public void topoSortByDFS() {
        // 先构建逆邻接表，边 s->t 表示，s 依赖于 t，t 先于 s
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            // 申请空间
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; ++i) {
            // 通过邻接表生成逆邻接表
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inverseAdj[w].add(i); // w->i
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; ++i) { // 深度优先遍历图
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    /**
     * 深度优先探测顶点可达的入度顶点
     * @param vertex
     * @param inverseAdj
     * @param visited
     */
    private void dfs(int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        } // 先把 vertex 这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.print("->" + vertex);
    }
}

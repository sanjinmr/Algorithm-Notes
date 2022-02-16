package com.sanjin.www.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author: sanji
 * email: sanjinmr@sina.com
 * time: 2020/2/21
 * desc: 无向图的表示和应用
 * note:
 */
public class GraphUndirected {

    // 顶点的个数
    private int v;

    // 邻接表
    private LinkedList<Integer>[] adjs;

    public GraphUndirected(int v) {
        this.v = v;

        adjs = new LinkedList[v];

        for (int i = 0; i < v; i ++) {
            adjs[i] = new LinkedList<>();
        }
    }

    /**
     * 存储每一条边的邻接关系
     * note: 无向图的每一条边存储两次（因为互为邻接顶点）
     * @param s
     * @param t
     */
    public void addEdge(int s, int t) {
        adjs[s].add(t);
        adjs[t].add(s);
    }

    /**
     * 广度优先搜索
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }

        // visited记录已被访问的顶点（把已被访问的顶点标记为true），避免重复访问
        boolean[] visited = new boolean[v];

        visited[s] = true;

        // queue是一个队列。存储已被访问的、但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);

        // prev用来记录搜索路径（即记录被访问的顶点是从哪个顶点访问的）
        int[] prev = new int[v];
        for (int i = 0; i < v; i ++) {
            prev[i] = -1;
        }

        while (queue.size() != 0) {
            int w = queue.poll();

            for (int i = 0; i < adjs[w].size(); i++) {
                int q = adjs[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                    }

                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    /**
     * 从最后一个位置，按照记录的访问路径打印出对应的顶点
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s , prev[t]);
        }
        System.out.print(t + " ");
    }

    private boolean found = false;

    /**
     * 深度优先搜索
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        found = false;

        boolean[] visited = new boolean[v];

        int[] prev = new int[v];
        for (int i = 0; i < v; i ++) {
            prev[i] = -1;
        }

        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) {
            return;
        }

        visited[w] = true;

        if (w == t) {
            found = true;
            return;
        }

        for (int i = 0; i < adjs[w].size(); i ++) {
            int q = adjs[w].get(i);

            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    public static void test() {
        GraphUndirected graph = new GraphUndirected(8);

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        graph.bfs(0, 6);
    }
}

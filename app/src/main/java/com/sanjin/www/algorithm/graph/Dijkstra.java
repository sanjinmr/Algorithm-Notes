package com.sanjin.www.algorithm.graph;

import java.util.LinkedList;

/**
 * Dijkstra算法
 *
 * 有向有权图的邻接表实现
 */
public class Dijkstra {

    private int v; // 顶点个数
    private LinkedList<Edge>[] adj; // 邻接表
    private Vertex[] vertices;

    public Dijkstra(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i ++) {
            adj[i] = new LinkedList<Edge>();
        }

        // 创建并初始化所有的顶点(和A*算法不同，A*算法的Vertex需要用x/y坐标创建，
        // 我们一般把它设计为一个外部接口，让调用该算法的人自己去创建，然后添加进来即可)
        vertices = new Vertex[this.v];
        for (int i = 0; i < this.v; i ++) {
            vertices[i] = new Vertex(i, Integer.MAX_VALUE);
        }
    }

    /**
     * 顶点
     */
    private static class Vertex {
        public int id; // 顶点编号ID
        public int dist; // 从起始顶点到这个顶点的距离
        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    /**
     * 边
     */
    private static class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 权重
        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }


    /**
     * 根据vertex.dist大小构建小顶堆
     */
    private class PriorityQueue {
        private Vertex[] nodes;
        private int count;
        public PriorityQueue(int v) {
            this.count = v;
            this.nodes = new Vertex[v + 1];
        }

        public Vertex poll() {
            // 待实现
            return null;
        }

        public void add(Vertex vertex) {
            // 待实现
        }

        /**
         * 更新节点的值，并堆化一次。时间复杂度O(logN)
         * @param vertex
         */
        public void update(Vertex vertex) {
            // 待实现
        }

        public boolean isEmpty() {
            // 待实现
            return false;
        }
    }


    /**
     * 从顶点S到顶点T的最短路径
     * @param s
     * @param t
     */
    public void dijkstra(int s, int t) {
        int[] prev = new int[this.v]; // 用来还原最短路径

        // 创建小根堆
        PriorityQueue queue = new PriorityQueue(this.v); // 小顶堆

        // 创建记录顶点是否正在队列中的备忘录
        // （帮助判断，是调用add还是update方法，当然，这个判断也可以封装到我们自定义的PriorityQueue实现）
        boolean[] visited = new boolean[this.v]; // 标记是否进正在队列中

        // 初始化s顶点
        vertices[s].dist = 0;
        queue.add(vertices[s]);
        visited[s] = true;

        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll(); // 取堆顶元素并删除
            visited[minVertex.id] = false; // 顶点一经移除队列，这里也标记一下

            if (minVertex.id == t) break; // 最短路径产生了

            for (int i = 0; i < adj[minVertex.id].size(); i ++) { // 更新当前顶点的邻接顶点距离值
                Edge e = adj[minVertex.id].get(i); // 取出一条minVertex相连的边
                Vertex nextVertex = vertices[e.tid]; // minVertex-->nextVertex

                if (minVertex.dist + e.w < nextVertex.dist) { // 更新next的dist
                    // 更新关联顶点的dist
                    nextVertex.dist = minVertex.dist + e.w;
                    prev[nextVertex.id] = minVertex.id;

                    // 更新小顶堆
                    if (visited[nextVertex.id]) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        visited[nextVertex.id] = true;
                    }
                }
            }
        }

        System.out.print(s);
        print(s, t, prev);
    }

    /**
     * 用方法的递归实现数组的栈式输出
     * @param s
     * @param t
     * @param prev
     */
    private void print(int s, int t, int[] prev) {
        if (s == t) return;
        print(s, prev[t], prev);
        System.out.print("->" + t);
    }

}

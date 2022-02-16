package com.sanjin.www.algorithm.graph;

import java.util.LinkedList;

/**
 * A* 搜索算法
 * 简单来说，A*启发式搜索算法是对Dijkstra算法的优化，
 * 它在最短路径的基础上定义了一个估价函数（估价函数=路径函数+启发函数），
 * 在小根堆中以估价函数作为节点大小的计算依据。作用是，避免在路径搜索的时候“走偏”。
 *
 */
public class AStarSearch {

    private int v; // 顶点个数
    private LinkedList<Edge>[] adj; // 邻接表
    private final Vertex[] vertexes;

    public AStarSearch(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i ++) {
            adj[i] = new LinkedList<Edge>();
        }

        // 创建图的顶点对象（和Dijkstra算法不同，A*的Vertex创建需要x\y坐标，一般让外部调用者自己创建）
        vertexes = new Vertex[this.v];
    }

    /**
     * 添加顶点的坐标
     * @param id
     * @param x
     * @param y
     */
    public void addVertex(int id, int x, int y) {
        vertexes[id] = new Vertex(id, x, y);
    }

    /**
     * 顶点
     */
    private class Vertex {
        public int id; // 顶点编号ID
        public int dist; // 从起始顶点，到这个顶点的距离，也就是g(i)
        public int f; // 新增：f(i)=g(i)+h(i)
        public int x, y; // 新增：顶点在地图中的坐标（x, y）
        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
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
     * 根据vertex.f大小构建小顶堆（Dijkstra是使用的vertex.dist，这是Dijkstra和A*算法的核心区别）
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

        public void clear() {
            // 待实现
        }
    }


    public void astar(int s, int t) { // 从顶点s到顶点t的路径
        int[] predecessor = new int[this.v]; // 用来还原路径

        // 按照vertex的f值构建的小顶堆，而不是按照dist
        PriorityQueue queue = new PriorityQueue(this.v);

        boolean[] inqueue = new boolean[this.v]; // 标记是否进入过队列

        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;

        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll(); // 取堆顶元素并删除
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                Edge e = adj[minVertex.id].get(i); // 取出一条minVetex相连的边
                Vertex nextVertex = vertexes[e.tid]; // minVertex-->nextVertex
                if (minVertex.dist + e.w < nextVertex.dist) { // 更新next的dist,f
                    nextVertex.dist = minVertex.dist + e.w;
                    nextVertex.f = nextVertex.dist+hManhattan(nextVertex, vertexes[t]);
                    predecessor[nextVertex.id] = minVertex.id;

                    if (inqueue[nextVertex.id]) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }

                if (nextVertex.id == t) { // 只要到达t就可以结束while了
                    queue.clear(); // 清空queue，才能退出while循环
                    break;
                }
            }
        }

        // 输出路径
        System.out.print(s);
        print(s, t, predecessor); // print函数请参看Dijkstra算法的实现
    }


    int hManhattan(Vertex v1, Vertex v2) { // Vertex表示顶点，后面有定义
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
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

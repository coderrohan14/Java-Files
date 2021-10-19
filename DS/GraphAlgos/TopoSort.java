package DS.GraphAlgo;

import java.util.*;

public class TopoSort {

    // Only for Directed Acyclic Graph(DAG).

    static int n, w;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] indegree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = sc.nextInt();
        indegree = new int[n + 1];
        adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < w; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            adj.get(a).add(b);
            indegree[b]++;
        }
        topoSort();
    }

    static void topoSort() { // Topological Sort(Sorting acc. to indegree of each vertex) using BFS.
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (Integer e : adj.get(u)) {
                indegree[e]--;
                if (indegree[e] == 0)
                    q.add(e);
            }
        }
    }
}

package DS.GraphAlgo;

import java.util.*;
import java.io.*;

/*
** Kosaraju's Algorithm - For Finding Strongly Connected Components in Directed Graph.
** Uses two DFS calls.
*/

public class Kosaraju {
    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007;

    public static void main(String[] args) throws IOException {
        if (System.getProperty("ONLINE_JUDGE") == null) {
            File f1 = new File("input.txt");
            File f2 = new File("output.txt");
            Reader r = new FileReader(f1);
            sc = new FastReader(r);
            out = new PrintWriter(f2);
            double prev = System.currentTimeMillis();
            solve();
            out.println("\n\nExecuted in : " + ((System.currentTimeMillis() - prev) / 1e3) + " sec");
        } else {
            sc = new FastReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            solve();
        }
        out.flush();
        out.close();
    }

    static int n, m;
    static ArrayList<ArrayList<Integer>> adj, trans;
    static ArrayList<Integer> order;
    static boolean[] vis;

    static void solve() {
        n = sc.nextInt();
        m = sc.nextInt();
        adj = new ArrayList<>(n + 1);
        order = new ArrayList<>();
        trans = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            trans.add(new ArrayList<>());
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            adj.get(a).add(b);
            trans.get(b).add(a);
        }
        vis = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!vis[i])
                dfs1(i);
        }
        vis = new boolean[n + 1];
        int cnt = 0;
        Collections.reverse(order);
        for (Integer e : order) {
            if (!vis[e]) {
                cnt++;
                dfs2(e);
            }
        }
        out.println("Number of SCC = " + cnt);
    }

    static void dfs1(int node) { // for getting the order of traversal in transpose graph
        vis[node] = true;
        for (Integer e : adj.get(node)) {
            if (!vis[e]) {
                dfs1(e);
            }
        }
        order.add(node);
    }

    static void dfs2(int node) { // DFS on transpose graph acc. to highest out time first.
        vis[node] = true;
        for (Integer e : trans.get(node)) {
            if (!vis[e])
                dfs2(e);
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(Reader r) {
            br = new BufferedReader(r);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] readArrayI(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            return arr;
        }

        long[] readArrayL(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLong();
            }
            return arr;
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
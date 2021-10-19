package DS.GraphAlgo;

import java.util.*;
import java.io.*;

public class Tarjan {
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

    static int n, m, in[], low[], timer, scc;
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] vis, onStack;
    static Stack<Integer> st;

    static void solve() {
        n = sc.nextInt();
        m = sc.nextInt();
        adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            adj.get(a).add(b);
        }
        scc = timer = 0;
        vis = new boolean[n + 1];
        in = new int[n + 1];
        low = new int[n + 1];
        onStack = new boolean[n + 1];
        st = new Stack<>();
        for (int i = 1; i <= n; i++) {
            if (!vis[i])
                dfs(i);
        }
    }

    static void dfs(int node) {
        vis[node] = true;
        in[node] = low[node] = timer++;
        onStack[node] = true;
        st.push(node);
        for (Integer e : adj.get(node)) {
            if (vis[e] && onStack[e]) {
                low[node] = Math.min(low[node], in[e]);
            } else if (!vis[e]) {
                dfs(e);
                if (onStack[e]) {
                    low[node] = Math.min(low[node], low[e]);
                }
            }
        }
        if (in[node] == low[node]) {
            scc++;
            out.println("SCC #" + scc + " ");
            int u;
            while (true) {
                u = st.pop();
                onStack[u] = false;
                out.print(u + " ");
                if (u == node)
                    break;
            }
            out.println();
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

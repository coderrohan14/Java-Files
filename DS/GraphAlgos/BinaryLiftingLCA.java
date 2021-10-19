package DS.GraphAlgo;

import java.util.*;
import java.io.*;

/* LCA of two nodes in a tree using Binary Lifting
   O(n*logn) time and space complexity for precomputation and then, O(logn) per query.*/

public class BinaryLiftingLCA {
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

    static int n, maxN, level[], LCA[][];
    static ArrayList<ArrayList<Integer>> adj;

    static void solve() {
        n = sc.nextInt();
        adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        int a, b;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        maxN = log2(n);
        level = new int[n + 1];
        LCA = new int[n + 1][maxN + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= maxN; j++) {
                LCA[i][j] = -1;
            }
        }
        init();
        // call getLCA(a,b) function here which runs in O(logn) time

    }

    static int getLCA(int a, int b) {
        if (level[b] < level[a]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int d = level[b] - level[a];
        while (d > 0) {
            int i = log2(d);
            b = LCA[b][i];
            d -= (1 << i);
        }
        if (a == b)
            return a;
        for (int i = maxN; i >= 0; i--) {
            if (LCA[a][i] != -1 && (LCA[a][i] != LCA[b][i])) {
                a = LCA[a][i];
                b = LCA[b][i];
            }
        }
        return LCA[a][0];
    }

    static void init() {
        dfs(1, 0, -1);
        for (int i = 1; i <= maxN; i++) {
            for (int j = 1; j <= n; j++) {
                if (LCA[j][i - 1] != -1) {
                    int par = LCA[j][i - 1];
                    LCA[j][i] = LCA[par][i - 1];
                }
            }
        }
    }

    static void dfs(int node, int lev, int par) {
        level[node] = lev;
        LCA[node][0] = par;
        for (Integer e : adj.get(node)) {
            if (e != par) {
                dfs(e, lev + 1, node);
            }
        }
    }

    static int log2(int N) {
        return (int) (Math.log10(N) / Math.log10(2));
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

package DS.GraphAlgo;

import java.util.*;
import java.io.*;

public class EdmondsKarp implements Runnable {

    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007, inf = (int) 1e9, minf = -(int) 1e9;
    static long infL = (long) 1e18, minfL = -(long) 1e18;

    public static void main(String[] args) {
        new Thread(null, new EdmondsKarp(), "coderrohan14", 1 << 26).start();
    }

    @Override
    public void run() {
        try {
            ioSetup();
        } catch (IOException e) {
            return;
        }
    }

    public static void ioSetup() throws IOException {
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

    static int n, m, capacity[][], source, sink;
    static ArrayList<ArrayList<Integer>> adj;

    static void solve() {
        n = sc.nextInt();
        m = sc.nextInt();
        capacity = new int[n + 1][n + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        int a, b, w;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            adj.get(a).add(b);
            w = sc.nextInt();
            capacity[a][b] = w;
        }
        source = sc.nextInt();
        sink = sc.nextInt();
        out.println(maxFlow());
    }

    static int maxFlow() {
        int flow = 0;
        int[] parent = new int[n + 1];
        int new_flow = 0, res = bfs(parent);
        while (res != 0) { // Runs till there is any augmenting path present
            new_flow = res;
            flow += new_flow;
            int curr = sink;
            while (curr != source) {
                int prev = parent[curr];
                capacity[prev][curr] -= new_flow;
                capacity[curr][prev] += new_flow; // for reverse edge residual capacity
                curr = prev;
            }
            res = bfs(parent);
        }
        return flow;
    }

    static int bfs(int[] parent) {
        Arrays.fill(parent, -1);
        parent[source] = -2;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(source, Integer.MAX_VALUE));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int curr = p.x;
            int flow = p.y;
            for (Integer next : adj.get(curr)) {
                if (parent[next] == -1 && capacity[curr][next] > 0) {
                    parent[next] = curr;
                    int new_flow = Math.min(flow, capacity[curr][next]);
                    if (next == sink)
                        return new_flow;
                    q.add(new Pair(next, new_flow));
                }
            }
        }
        return 0;
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
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

        boolean hasNext() {
            if (st != null && st.hasMoreTokens()) {
                return true;
            }
            String tmp;
            try {
                br.mark(1000);
                tmp = br.readLine();
                if (tmp == null) {
                    return false;
                }
                br.reset();
            } catch (IOException e) {
                return false;
            }
            return true;
        }
    }
}
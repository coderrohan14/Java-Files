package DS.GraphAlgo;

import java.util.*;
import java.io.*;

public class BellmanFord implements Runnable {

    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007, inf = (int) 1e9, minf = -(int) 1e9;
    static long infL = (long) 1e18, minfL = -(long) 1e18;

    public static void main(String[] args) {
        new Thread(null, new BellmanFord(), "coderrohan14", 1 << 26).start();
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

    static int n, m, s, dist[];
    static ArrayList<Edge> el; // Edge List

    static void solve() { // Used over Dijkstra when there is any negative weight cycle in graph.
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
        el = new ArrayList<>();
        int a, b, w;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            w = sc.nextInt();
            el.add(new Edge(a, b, w));
        }
        dist = new int[n + 1];
        Arrays.fill(dist, inf);
        dist[s] = 0;
        for (int i = 0; i < n - 1; i++) { // (n-1) Phases
            boolean ch = false;
            for (int j = 0; j < m; j++) { // Traversing on Edge List
                if (dist[el.get(j).a] < inf) { // In (a->b), a is already visited/marked.
                    if (dist[el.get(j).b] > dist[el.get(j).a] + el.get(j).cost) {
                        dist[el.get(j).b] = dist[el.get(j).a] + el.get(j).cost;
                        ch = true;
                    }
                }
            }
            if (!ch)
                break;
        }
        boolean neg = false;
        for (int i = 0; i < m; i++) {
            if (dist[el.get(i).a] < inf) {
                if (dist[el.get(i).b] > dist[el.get(i).a] + el.get(i).cost) {
                    neg = true;
                    break;
                }
            }
        }
        if (neg) {
            out.println("Shortest Distances not found...negative weight cycle present in Graph!");
        } else {
            // Distance array stores the shortest path of every node from source
            // node(s).....
            for (int i = 1; i <= n; i++) {
                out.print(dist[i] + " ");
            }
        }
    }

    static class Edge {
        int a, b, cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
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
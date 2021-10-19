package DS.GraphAlgo;

import java.util.*;
import java.io.*;

public class Dijkstra implements Runnable {

    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007, inf = (int) 1e9, minf = -(int) 1e9;
    static long infL = (long) 1e18, minfL = -(long) 1e18;

    public static void main(String[] args) {
        new Thread(null, new Dijkstra(), "coderrohan14", 1 << 26).start();
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

    static ArrayList<ArrayList<Pair>> adj;
    static int n, m;
    static long[] dist;
    static PriorityQueue<Pair> pq;

    static void solve() { // For Undirected, weighted graph.
        n = sc.nextInt();
        m = sc.nextInt();
        adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        int a, b, w;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            w = sc.nextInt();
            adj.get(a).add(new Pair(b, w));
            adj.get(b).add(new Pair(a, w));
        }
        init();
        // Distances from the source are stored in the distance array now...
    }

    static void init() {
        dist = new long[n + 1];
        Arrays.fill(dist, infL);
        pq = new PriorityQueue<>((x, y) -> {
            return (int) (x.y - y.y);
        }); // pairs as (node,distance) ---> Min. acc. to distance.
        pq.add(new Pair(1, 0)); // Assuming 1 as the source.....change accordingly.
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int curr_node = p.x;
            long curr_dis = p.y;
            for (Pair e : adj.get(curr_node)) {
                if (curr_dis + e.y < dist[e.x]) {
                    dist[e.x] = curr_dis + e.y;
                    pq.add(new Pair(e.x, dist[e.x]));
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> { // Pair Class
        int x;
        long y;

        public Pair(int x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pair)) {
                return false;
            }

            Pair pair = (Pair) o;

            if (x != pair.x) {
                return false;
            }
            if (y != pair.y) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            long result = x;
            result = 31 * result + y;
            return (int) result;
        }

        @Override
        public int compareTo(Pair o) {
            return (int) (this.x - o.x);
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
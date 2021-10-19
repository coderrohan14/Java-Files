//package numberTheory;

import java.util.*;
import java.io.*;

public class SegmentedSieve {

    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007;

    public static void main(String[] args) throws IOException {
        sc = new FastReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.flush();
        out.close();
    }

    static ArrayList<Long> primeList;

    static void solve() {
        primeList = new ArrayList<>();
        segSieve((long) 1e9);
        out.println(primeList.size());
    }

    static void simpleSieve(int limit, ArrayList<Long> prime) {
        boolean mark[] = new boolean[limit + 1];
        Arrays.fill(mark, true);
        for (int p = 2; p * p < limit; p++) {
            if (mark[p]) {
                for (int i = p * p; i < limit; i += p)
                    mark[i] = false;
            }
        }
        for (int p = 2; p < limit; p++) {
            if (mark[p]) {
                prime.add((long) p);
                primeList.add((long) p);
            }
        }
    }

    static void segSieve(long n) {
        int limit = (int) (Math.floor(Math.sqrt(n)) + 1);
        ArrayList<Long> prime = new ArrayList<>();
        simpleSieve(limit, prime);
        long low = limit;
        long high = 2 * limit;
        while (low < n) {
            if (high >= n)
                high = n;
            boolean mark[] = new boolean[limit + 1];
            for (int i = 0; i < mark.length; i++)
                mark[i] = true;
            for (int i = 0; i < prime.size(); i++) {
                int loLim = (int) ((low / prime.get(i)) * prime.get(i));
                if (loLim < low)
                    loLim += prime.get(i);
                for (long j = loLim; j < high; j += prime.get(i))
                    mark[(int) (j - low)] = false;
            }
            for (long i = low; i < high; i++)
                if (mark[(int) (i - low)]) {
                    primeList.add(i);
                }
            low = low + limit;
            high = high + limit;
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
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class TestGenerator {

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
            // out.println("\n\nExecuted in : " + ((System.currentTimeMillis() - prev) / 1e3) + " sec");
        } else {
            sc = new FastReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            solve();
        }
        out.flush();
        out.close();
    }

    static void solve() {
        int t = 10;
        out.println(t);
        for (int i = 0; i < t; i++) {
            // int n = ThreadLocalRandom.current().nextInt(1, (int) 1e5);
            // int q = ThreadLocalRandom.current().nextInt(900, (int) 1e3);
            // out.println(n + " " + q);
            int n = ThreadLocalRandom.current().nextInt(1, (int) 1e5);
            int q = ThreadLocalRandom.current().nextInt(1, (int) 1e3);
            out.println(n+" "+q);
            for (int cnt = 0; cnt < n;cnt++) {
                int step = ThreadLocalRandom.current().nextInt(0, 2);
                int inc = ThreadLocalRandom.current().nextInt(0, (int)1e3);
                if(step==0){
                    out.print(inc+" ");
                }else{
                    out.print((-inc)+" ");
                }
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
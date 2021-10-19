package DS.GraphAlgos;

import java.util.*;
import java.io.*;
 
public class FloydWarshall implements Runnable {
 
    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007, inf = (int) 1e9, minf = -(int) 1e9;
    static long infL = (long) 1e18, minfL = -(long) 1e18;
    static final Random random = new Random();
 
    public static void main(String[] args) {
        new Thread(null, new FloydWarshall(), "coderrohan14", 1 << 26).start();
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

    static int n,m,dist[][];    // O(V^3)
 
    static void solve() {
        n = sc.nextInt();
        m = sc.nextInt();
        dist = new int[n+1][n+1];
        int x,y,w;
        for(int i=0;i<=n;i++){
            Arrays.fill(dist[i],inf);
        }
        for(int i=0;i<m;i++){
            x=sc.nextInt();
            y=sc.nextInt();
            w=sc.nextInt();
            dist[x][y] = w;
            dist[y][x] = w;
        }
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(i==j){
                        dist[i][j] = 0;
                        continue;
                    }
                    if(dist[i][k]==inf||dist[k][j]==inf) continue;
                    dist[i][j] = Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                }
            }
        }
        // Print The Distance Array :->
        // for(int i=1;i<=n;i++){
        //     for(int j=1;j<=n;j++){
        //         out.print(dist[i][j]+" ");
        //     }
        //     out.println();
        // }
    }

    static class Pair{
        int x,y;
        Pair(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
 
    /**************************************************************************************************************************************************************************************/
 
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

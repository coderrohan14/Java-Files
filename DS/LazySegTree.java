package DS;
import java.util.*;
import java.io.*;
 
public class LazySegTree implements Runnable {
 
    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007, inf = (int) 1e9, minf = -(int) 1e9;
    static long infL = (long) 1e18, minfL = -(long) 1e18;
    static final Random random = new Random();
 
    public static void main(String[] args) {
        new Thread(null, new LazySegTree(), "coderrohan14", 1 << 26).start();
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

    // Segment Tree with lazy propogation.

    static int n,tree[]; // tree[x] stores the sum in node x;
                         // node V -> (2*V) and (2*V+1)

    static boolean lazy[];  // tells if a certain node is storing any unpropogated update or not.
    static int upd[];       // the value of the unpropogated updates

    static void solve() {
        n=sc.nextInt();
        tree = new int[4*n];
        lazy = new boolean[4*n];
        upd = new int[4*n];

        update(1,0,n-1,2,5,3); // Add "3" to all leaves in the range [2,5].
        out.println(query(1,0,n-1,2, 5));
    }

    /*
        [tl,tr]  --> Node Range
        v --> current  node id
        val --> value to be added to all the leaves of the current node.
    */

    // this is the process of telling the node to remember "val"

    static void apply(int v,int tl,int tr,int val){
        tree[v] += val * (tr-tl+1); // Set the correct information at the node.
        if(tl!=tr){ // If it is not a leaf node, mark it lazy.
            lazy[v] = true;
            upd[v] += val;
        }
    }

    // Assigning the task to both of it's child nodes(whenever we go down from that node).

    static void pushDown(int v,int tl,int tr){
        if(lazy[v]){ // If it is lazy, propogate it.
            lazy[v] = false; // Not lazy anymore.
            int tm = (tl + tr)/2;
            apply(2*v,tl,tm,upd[v]); // Propogate on the left child
            apply(2*v+1,tm+1,tr,upd[v]); // Propogate on the right child.
            upd[v] = 0; // update done(node doesn't remember any value now).
        }
    }

    // [l,r] --> Update Range 
    // val --> Update value, add this to all leaves in range [l,r].

    static void update(int v,int tl,int tr,int l,int r,int val){
        if(tl>r||tr<l){ // No overlap
            return;
        }
        if(l<=tl && tr<=r){ // Fully within
            apply(v,tl,tr,val);
            return;
        }
        // Partial Overlap

        pushDown(v, tl, tr); // Removing lazy tag and passing information to children.

        int tm = (tl + tr)/2;
        update(2*v, tl, tm, l, r, val);
        update(2*v+1, tm+1, tr, l, r, val);
        tree[v] = tree[2*v] + tree[2*v+1]; // Store correct value at this node.
    }

    static int query(int v,int tl,int tr,int l,int r){
        if(tl>r||tr<l){ // no overlap.
            return 0;
        }
        // fully overlap.
        if(l<=tl&&r>=tr){   // l....tl....tr....r
            return tree[v];
        }
        // partial overlap.

        pushDown(v, tl, tr); // We remove the lazy tag before going down.
                             // Why? So that it's children have correct information.

        int tm = (tl+tr)/2; // [tl,tm] and [tm+1,tr]
        int ans = 0;
        ans+=query(2*v, tl, tm, l, r);
        ans+=query(2*v+1, tm+1, tr, l, r);
        return ans;
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

    static void printArray(int[] arr) {
        out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1)
                out.print(arr[i] + ",");
            else
                out.print(arr[i]);
        }
        out.print("]");
        out.println();
    }
}

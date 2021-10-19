import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
 
public class Test implements Runnable {
 
    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007, inf = (int) 1e9, minf = -(int) 1e9;
    static long infL = (long) 1e18, minfL = -(long) 1e18;
    static final Random random = new Random();
 
    public static void main(String[] args) {
        new Thread(null, new Test(), "coderrohan14", 1 << 26).start();
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
 
    static void solve() {
        int t = sc.nextInt();
        StringBuilder ans = new StringBuilder("");
        while (t-- > 0) {
            
        }
        out.println(ans);
    }
 
    /****************************************************************************************************************************************************************************************/
 
    public static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    static double setPrecision(double num,int precision){
        return BigDecimal.valueOf(num).setScale(precision,RoundingMode.HALF_UP).doubleValue();
    }
 
    static long modInverse(long a, int mod) {
        long g = gcd(a, mod);
        if (g != 1)
            return -1;
        else {
            return modPower(a, mod - 2L, mod);
        }
    }
 
    static long modPower(long x, long y, int mod) {
        long res = 1;
        x = x % mod;
        if (x == 0)
            return 0;
        while (y > 0) {
            if ((y & 1) != 0)
                res = (res * x) % mod;
            y = y >> 1;
            x = (x * x) % mod;
        }
        return res;
    }
 
    static int gcd(int a, int b) {
        int tmp = 0;
        while (b != 0) {
            tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
 
    static long gcd(long a, long b) {
        long tmp = 0;
        while (b != 0) {
            tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
 
    static boolean isPrime(long n) {
        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0)
            return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return n != 1;
    }
 
    static void sort(long[] a) {
        int n = a.length;// shuffle, then sort
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n);
            long temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }
 
    static void sort(int[] a) {
        int n = a.length;// shuffle, then sort
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }
 
    static boolean isPerfectSquare(long x) {
        long sqrt = (long) Math.sqrt(x);
        return (sqrt * sqrt) == x;
    }
 
    static int digitsCount(long x) {
        return (int) Math.floor(Math.log10(x)) + 1;
    }
 
    static boolean isPowerTwo(long n) {
        return (n & n - 1) == 0;
    }
 
    static void sieve(boolean[] prime, int n) { // Sieve Of Eratosthenes
        for (int i = 1; i <= n; i++) {
            prime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = 2; i * j <= n; j++) {
                    prime[i * j] = false;
                }
            }
        }
    }
 
    static long nCr(long n, long r) { // Combinations
        if (n < r)
            return 0;
        if (r > n - r) { // because nCr(n, r) == nCr(n, n - r)
            r = n - r;
        }
        long ans = 1L;
        for (long i = 0; i < r; i++) {
            ans *= (n - i);
            ans /= (i + 1);
        }
        return ans;
    }
 
    static int floor(int[] a, int v) {
        int l = 0, h = a.length - 1;
        while (l < h) {
            int mid = (l + h) / 2;
            if (a[mid] == v)
                return mid;
            if (v < a[mid])
                h = mid;
            else {
                if (mid + 1 < h && a[mid + 1] < v)
                    l = mid + 1;
                else
                    return mid;
            }
        }
        return a[l] <= v ? l : -1;
    }
 
    static int floor(long[] a, long v) {
        int l = 0, h = a.length - 1;
        while (l < h) {
            int mid = (l + h) / 2;
            if (a[mid] == v)
                return mid;
            if (v < a[mid])
                h = mid;
            else {
                if (mid + 1 < h && a[mid + 1] < v)
                    l = mid + 1;
                else
                    return mid;
            }
        }
        return a[l] <= v ? l : -1;
    }
 
    static int ceil(int[] a, int v) {
        int l = 0, h = a.length - 1;
        while (l < h) {
            int mid = (l + h) / 2;
            if (a[mid] == v)
                return mid;
            if (a[mid] < v)
                l = mid + 1;
            else
                h = mid;
        }
        return a[h] >= v ? h : -1;
    }
 
    static int ceil(long[] a, long v) {
        int l = 0, h = a.length - 1;
        while (l < h) {
            int mid = (l + h) / 2;
            if (a[mid] == v)
                return mid;
            if (a[mid] < v)
                l = mid + 1;
            else
                h = mid;
        }
        return a[h] >= v ? h : -1;
    }
 
    static long catalan(int n) { // n-th Catalan Number
        long c = nCr(2 * n, n);
        return c / (n + 1);
    }
 
    static class Pair implements Comparable<Pair> { // Pair Class
        int x;
        int y;
 
        public Pair(int x, int y) {
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
 
    static class Trip { // Triplet Class
        long x;
        long y;
        long z;
 
        Trip(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
 
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Trip)) {
                return false;
            }
 
            Trip trip = (Trip) o;
 
            if (x != trip.x) {
                return false;
            }
            if (y != trip.y) {
                return false;
            }
            if (z != trip.z) {
                return false;
            }
            return true;
        }
 
        @Override
        public int hashCode() {
            long result = 62 * x + 31 * y + z;
            return (int) result;
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
 
    /*
     * ASCII Range--->(A-Z)--->[65,90]<<::>>(a-z)--->[97,122]
     */
    /******************************************************************************************************************/
 
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
 
    static void printArray(long[] arr) {
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
 
    static void printArray(double[] arr) {
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
    /**********************************************************************************************************************/
}

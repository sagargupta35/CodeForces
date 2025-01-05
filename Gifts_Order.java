import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int q = in.nextInt();
            long [] arr = al(n);
            SegTree st = new SegTree();
            st.build(arr);
            System.out.println(Math.max(st.seg[0].ans1, st.seg[0].ans2));
            while (q-- > 0){
                int p = in.nextInt()-1;
                int x = in.nextInt();
                st.update(p, x);
                System.out.println(Math.max(st.seg[0].ans1, st.seg[0].ans2));
            }
        }
    }

    static class Info{
        long min1, min2, max1, max2, ans1, ans2;

        public Info(long min1, long min2, long max1, long max2, long ans1, long ans2) {
            this.min1 = min1;
            this.min2 = min2;
            this.max1 = max1;
            this.max2 = max2;
            this.ans1 = ans1;
            this.ans2 = ans2;
        }

    }

    static class SegTree{

        Info [] seg;
        int n;

        void build(long [] arr){
            this.n = arr.length;
            seg = new Info[4 * arr.length];
            build(arr, 0, 0, n-1);
        }

        private void build(long [] arr, int idx, int lo, int hi){
            if(lo == hi){
                seg[idx] = new Info(
                        arr[lo] - lo, arr[lo] + lo, arr[lo] - lo, arr[lo] + lo, 0, 0
                );
                return;
            }

            int mid = lo + (hi - lo)/2;
            build(arr, 2*idx + 1, lo, mid);;
            build(arr, 2*idx +2, mid+1, hi);;

            updateCur(idx);
        }

        void updateCur(int idx){
            Info left = seg[2*idx+1];
            Info right = seg[2*idx+2];

            Info cur = new Info(
                    Math.min(left.min1, right.min1), Math.min(left.min2, right.min2),
                    Math.max(left.max1, right.max1), Math.max(left.max2, right.max2),
                    Math.max(Math.max(left.ans1, right.ans1), right.max1 - left.min1),
                    Math.max(Math.max(left.ans2, right.ans2), left.max2 - right.min2)
            );

            seg[idx] = cur;
        }

        void update(int p, int x){
            update(p, x, 0, 0, n-1);
        }

        void update(int p, int x, int idx, int lo, int hi){
            if(lo == hi){
                seg[idx] = new Info(
                        x - p, x+p, x-p, x+p, 0, 0
                );
                return;
            }

            int mid = lo + (hi - lo)/2;
            if(p <= mid) update(p, x, 2*idx+1, lo, mid);
            else update(p, x, 2*idx+2, mid+1, hi);

            updateCur(idx);
        }

    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

    }

    static long nCrModP(int n, int[] fac, int[] mods, int r, int p) {

        if (n < r)
            return 0;

        if (r == 0)
            return 1;

        return ((long) fac[n] * mods[r]
                % p * mods[n - r]
                % p)
                % p;
    }

    static long modInverse(int n, int p) {
        return pow(n, p - 2, p);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }

    static FastReader in = new FastReader();


    static class FastWriter {
        private final BufferedWriter bw;


        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }
        public void close() throws IOException {
            bw.close();
        }

    }

    static int[] ai(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
        return arr;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int aMax(int[] arr) {
        int a = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) a = Math.max(a, arr[i]);
        return a;
    }

    static int aMin(int[] arr) {
        int a = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) a = Math.min(a, arr[i]);
        return a;
    }

    static int mod = (int) 1e9 + 7;

    static long lcm(long u, long v) {
        return (u / gcd(u, v)) * v;
    }

    static long sumOfNumbers(long start, long end) {
        if (end < start) return 0;
        else if (end == start) return end;
        return (((end * (end + 1)) / 2) - ((start * (start - 1)) / 2));
    }

    static long pow(long a, long b, int mod) {
        long res = 1;
        while (b > 0) {
            if ((b&1) == 1) {
                res = (res * a % mod) % mod;
            }
            a = (a % mod * a % mod) % mod;
            b >>= 1;
        }
        return res % mod;
    }

    static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b&1) == 1) {
                res = (res * a % mod) % mod;
            }
            a = (a % mod * a % mod) % mod;
            b >>= 1;
        }
        return res % mod;
    }


    static class Triplet<T> {
        T x, y, z;

        public Triplet(T x, T y, T z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static Triplet<Long> extendedEuclid(long a, long b) { // a>b
        if (b == 0) {
            return new Triplet<>(a, 0L, a);
        }

        Triplet<Long> smallAns = extendedEuclid(b, a % b);
        long y = smallAns.x - (a / b) * smallAns.y;
        return new Triplet<>(smallAns.y, y, smallAns.z);

    }

    static long modularMultiplicativeInverse(long a, long m) {
        long gcd = gcd(a, m);
        if (gcd != 1) {
            return -1;
        }
        long x = extendedEuclid(a, m).x;
        x = (x % m + m) % m;
        return x;
    }


    static long pow(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                res = (res * a % mod) % mod;
            }
            a = (a % mod * a % mod) % mod;
            b >>= 1;
        }
        return res % mod;
    }

    static long bigPow(long base, long pow, long mod) {
        BigInteger res = new BigInteger("1");
        BigInteger bBase = BigInteger.valueOf(base);
        BigInteger mod2 = BigInteger.valueOf(mod);
        while (pow > 0) {
            if (pow % 2 == 1) {
                res = res.multiply(bBase);
                res = res.mod(mod2);
            }
            bBase = bBase.pow(2);
            bBase = bBase.mod(mod2);
            pow >>= 1;
        }
        res = res.mod(BigInteger.valueOf(mod));
        return res.longValue();
    }

    static boolean millerRobin(long n, long d, long a) {
        long x = bigPow(a, d, n);

        if (x == 1 || x == n - 1) return true;

        while (d != n - 1) {
            BigInteger bx = BigInteger.valueOf(x);
            bx = bx.multiply(bx).mod(BigInteger.valueOf(n));
            x = bx.longValue();
            d *= 2;

            if (x == 1) return false;
            if (x == n - 1) return true;
        }

        return false;
    }

    public static boolean isPrime(long n) {
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;
        if (n % 2 == 0) return false;

        long d = n - 1;
        while ((d & 1) == 0) d /= 2;

        long[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        for (long j : arr) {
            if (n == j) return true;
            if (!millerRobin(n, d, j)) return false;
        }

        return true;
    }

    static long nCrModPFermat(int n, int r, int p, long[] fac) {
        if (n < r) return 0;
        if (r == 0) return 1;

        return ((fac[n] * modularMultiplicativeInverse(fac[r], p)) % p *
                (modularMultiplicativeInverse(fac[n - r], p)) % p) % p;
    }
}

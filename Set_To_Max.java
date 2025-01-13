import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int [] arr = ai(n);
            int [] b = ai(n);
            boolean can = true;
            for (int i = 0; i < n; i++) {
                if(arr[i] > b[i]){
                    can = false;
                    break;
                }
            }
            if(!can){
                System.out.println("NO");
                continue;
            }
            SegTree sa = new SegTree();
            SegTree sb = new SegTree();
            sa.build(arr);
            sb.build(b);
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(p -> b[p.f]));
            NavigableSet<Integer> [] nei = new TreeSet [n+1];
            Arrays.setAll(nei, i -> new TreeSet<>());
            for (int i = 0, pre = 0; i < n; i++) {
                nei[arr[i]].add(i);
                if(b[i] != b[pre]){
                    pq.add(new Pair<>(pre, i-1));
                    pre = i;
                }
                if(i == n-1){
                    pq.add(new Pair<>(pre, i));
                }
            }
            while (!pq.isEmpty()){
                var p = pq.poll();
                int l = p.f, r = p.s, cur = b[r];
                Integer left = nei[cur].floor(r);
                if(left != null){
                    int max = sa.max(left, r);
                    if(max == cur){
                        int min = sb.min(left, r);
                        if(min == cur){
                            nei[cur].add(r);
                            sa.update(0, n-1, left, r, 0, cur);
                            continue;
                        }
                    }
                }
                Integer right = nei[b[r]].ceiling(l);
                if(right != null){
                    int max = sa.max(l, right);
                    if(max == cur){
                        int min = sb.min(l, right);
                        if(min == cur){
                            nei[cur].add(l);
                            sa.update(0, n-1, l, right, 0, cur);
                        } else{
                            can = false;
                            break;
                        }
                    } else{
                        can = false;
                        break;
                    }
                } else{
                    can = false;
                    break;
                }
            }
            if(can){
                System.out.println("YES");
                continue;
            }
            System.out.println("NO");
        }
    }

    static class SegTree{
        int n;
        int [] min;
        int [] max;

        void build(int [] arr){
            n = arr.length;
            min = new int [4*n];
            max = new int [4*n];
            build(arr, 0, n-1, 0);
        }

        void build(int [] arr, int l, int r, int idx){
            if(l == r){
                max[idx] = arr[l];
                min[idx] = arr[l];
                return;
            }
            int mid = l + (r - l)/2;
            build(arr, l, mid, 2*idx+1);;
            build(arr, mid+1, r, 2*idx+2);;
            max[idx] = Math.max(max[2*idx+1], max[2*idx+2]);
            min[idx] = Math.min(min[2*idx+1], min[2*idx+2]);
        }

        int max(int l, int r){
            return max(0, n-1, l, r, 0);
        }

        int max(int lo, int hi, int l, int r, int idx){
            if(lo > r || hi < l) return Integer.MIN_VALUE;
            if(lo >= l && hi <= r) return max[idx];
            int mid = lo + (hi - lo)/2;
            return Math.max(max(lo, mid, l, r, 2*idx+1), max(mid+1, hi, l, r, 2*idx+2));
        }

        int min(int l, int r){
            return min(0, n-1, l, r, 0);
        }

        int min(int lo, int hi, int l, int r, int idx){
            if(lo > r || hi < l) return Integer.MAX_VALUE;
            if(lo >= l && hi <= r) return min[idx];
            int mid = lo + (hi - lo)/2;
            return Math.min(min(lo, mid, l, r, 2*idx+1), min(mid+1, hi, l, r, 2*idx+2));
        }

        void update(int lo, int hi, int l, int r, int idx, int val){
            if(lo > r || hi < l) return;
            if(lo >= l && hi <= r){
                max[idx] = val;
                return;
            }
            int mid = lo + (hi - lo)/2;
            update(lo, mid, l, r, 2*idx+1, val);
            update(mid+1, hi, l, r, 2*idx+2, val);
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

import java.io.*;
import java.lang.reflect.Member;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        String b = in.nextLine();
        String p = in.nextLine();
        Queue<Integer> cm = new LinkedList<>(), ce = new LinkedList<>(), mc = new LinkedList<>(), me = new LinkedList<>(), ec = new LinkedList<>(), em = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(b.charAt(i) != p.charAt(i)){
                if(b.charAt(i) == 'C'){
                    if(p.charAt(i) == 'M') cm.add(i+1);
                    else ce.add(i+1);
                } else if(b.charAt(i) == 'M'){
                    if(p.charAt(i) == 'C') mc.add(i+1);
                    else me.add(i+1);
                } else{
                    if(p.charAt(i) == 'C') ec.add(i+1);
                    else em.add(i+1);
                }
            }
        }
        List<String> res = new ArrayList<>();
        while (!ec.isEmpty()){
            int j = ec.poll();
            if(cm.isEmpty()){
                if(!ce.isEmpty()){
                    drive(j, res); pickup(res);
                    drive(ce.poll(), res);
                    dropOff(res);
                }
            } else {
                drive(j, res); pickup(res);
                drive(cm.poll(), res); dropOff(res);
                deliverCM(mc, cm, ce, me, res);
            }
        }
        while (!em.isEmpty()){
            int j = em.poll();
            if(mc.isEmpty()){
                if(!me.isEmpty()){
                    drive(j, res); pickup(res);
                    drive(me.poll(), res);
                    dropOff(res);
                }
            } else{
                drive(j, res); pickup(res);
                drive(mc.poll(), res); dropOff(res);
                deliverMC(mc, cm, ce, me, res);
            }
        }
        System.out.println(res.size());
        for(var s : res) System.out.println(s);
    }

    static void deliverCM(Queue<Integer> mc, Queue<Integer> cm, Queue<Integer> ce, Queue<Integer> me, List<String> res){
        boolean flag = false;
        if(!mc.isEmpty() || !me.isEmpty()) pickup(res);
        while (true){
            if(!flag){
                if(mc.isEmpty()){
                    if(!me.isEmpty()){
                        drive(me.poll(), res);
                        dropOff(res);
                    }
                    break;
                }
                drive(mc.poll(), res);
            } else{
                if(cm.isEmpty()){
                    if(!ce.isEmpty()){
                        drive(ce.poll(), res);
                        dropOff(res);
                    }
                    break;
                }
                drive(cm.poll(), res);
            }
            dropOff(res);
            pickup(res);
            flag = !flag;
        }
    }

    static void deliverMC(Queue<Integer> mc, Queue<Integer> cm, Queue<Integer> ce, Queue<Integer> me, List<String> res){
        boolean flag = false;
        if(!cm.isEmpty() || !ce.isEmpty()) pickup(res);
        while (true){
            if(!flag){
                if(cm.isEmpty()){
                    if(!ce.isEmpty()){
                        drive(ce.poll(), res);
                        dropOff(res);
                    }
                    break;
                }
                drive(cm.poll(), res);
            } else{
                if(mc.isEmpty()){
                    if(!me.isEmpty()){
                        drive(me.poll(), res);
                        dropOff(res);
                    }
                    break;
                }
                drive(mc.poll(), res);
            }
            flag = !flag;
            dropOff(res);
            pickup(res);
        }
    }

    static void pickup(List<String> res){
        res.add("PICKUP");
    }

    static void dropOff(List<String> res){
        res.add("DROPOFF");
    }

    static void drive(int j, List<String> res){
        res.add("DRIVE " + j);
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

    static FastReader in = new FastReader();

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

 import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int [] arr = ai(n);
        int [] sorted = Arrays.copyOf(arr, n);
        Arrays.sort(sorted);
        int m = in.nextInt();
        while (m-- > 0){
            int k = n-in.nextInt();
            int p = in.nextInt();
            int cnt = 0;
            for (int i = k; i < n; i++) {
                if(sorted[i] != sorted[k]) break;
                cnt++;
            }
            int prev = -1;
            for (int i = 0; i < n; i++) {
                if(arr[i] == sorted[k]){
                    if(cnt > 0){
                        cnt--;
                        prev = arr[i];
                        p--;
                    }
                } else if(arr[i] > sorted[k]){
                    prev = arr[i];
                    p--;
                }
                if(p == 0) break;
            }
            System.out.println(prev);
        }
    }

    static boolean flip(int ops, int num){
        if(ops >= num){
            return (ops & 1) == (num & 1);
        }
        return false;
    }

    static int gcd(int [] arr){
        int gcd = arr[0];
        for(int i = 1; i<arr.length; i++){
            gcd = (int) gcd(gcd, arr[i]);
        }
        return gcd;
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec){
         this.f = first;
         this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

    }

    static long nCrModP(int n, int [] fac, int [] mods,  int r, int p) {

        if (n<r)
            return 0;

        if (r == 0)
            return 1;

        return ((long) fac[n] * mods[r]
                % p * mods[n-r]
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
    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
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

    static long lcm(long u, long v) {
        return (u / gcd(u, v)) * v;
    }

    static int mod = (int) 1e9 + 7;

    static long sumOfNumbers(long start, long end){
        if(end < start) return 0;
        else if(end == start) return end;
        return (((end*(end+1))/2) - ((start*(start-1))/2));
    }

    static long pow(long a, long b){
        long res = 1;
        while(b > 0){
            if(b%2 == 1){
                res = (res * a%mod)%mod;
            }
            a = (a%mod*a%mod)%mod;
            b >>= 1;
        }
        return res%mod;
    }

    static class  Triplet<T>{
        T x, y, z;

        public Triplet(T x, T y, T z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static Triplet<Long> extendedEuclid(long a, long b){ // a>b
        if(b == 0){
            return new Triplet<>(a, 0L, a);
        }

        Triplet<Long> smallAns = extendedEuclid(b, a%b);
        long y = smallAns.x - (a/b)*smallAns.y;
        return new Triplet<>(smallAns.y, y, smallAns.z);

    }

    static long modularMultiplicativeInverse(long a, long m){
        long gcd = gcd(a, m);
        if(gcd != 1){
            return -1;
        }
        long x = extendedEuclid(a, m).x;
        x = (x%m + m)%m;
        return x;
    }

    static long pow(long a, long b, long mod){
        long res = 1;
        while(b > 0){
            if(b%2 == 1){
                res = (res * a%mod)%mod;
            }
            a = (a%mod*a%mod)%mod;
            b >>= 1;
        }
        return res%mod;
    }

    static long bigPow(long base, long pow, long mod){
        BigInteger res = new BigInteger("1");
        BigInteger bBase = BigInteger.valueOf(base);
        BigInteger mod2 = BigInteger.valueOf(mod);
        while (pow > 0){
            if(pow%2 == 1){
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

    static boolean millerRobin(long n, long d, long a){
        long x = bigPow(a, d, n);

        if(x == 1 || x == n-1) return true;

        while (d != n-1){
            BigInteger bx = BigInteger.valueOf(x);
            bx = bx.multiply(bx).mod(BigInteger.valueOf(n));
            x = bx.longValue();
            d *= 2;

            if(x == 1) return false;
            if(x == n-1) return true;
        }

        return false;
    }

    public static boolean isPrime(long n){
        if(n <= 1 || n== 4) return false;
        if(n <= 3) return true;
        if(n%2 == 0) return false;

        long d = n-1;
        while ((d&1) == 0) d /= 2;

        long [] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        for(long j : arr){
            if(n == j) return true;
            if(!millerRobin(n, d, j)) return false;
        }

        return true;
    }

    static int [] sieve(int n){
        int [] sieve = new int [n+1];
        sieve[0] = sieve[1] = 1;
        for(int i = 2; i<=n; i++){
            if(sieve[i] == 0){
                sieve[i] = i;
                for(int j = i*2; j<=n; j += i){
                    sieve[j] = i;
                }
            }
        }
        return sieve;
    }

    static long nCrModPFermat(int n, int r, int p) {
        if (n<r) return 0;
        if (r == 0) return 1;

        long[] fac = new long[n + 1];
        fac[0] = 1;

        for (int i = 1; i <= n; i++)
            fac[i] = fac[i - 1] * i % p;

        return ((fac[n] * modularMultiplicativeInverse(fac[r], p)) % p *
                (modularMultiplicativeInverse(fac[n - r], p)) % p) % p;
    }

    static long nCrModPFermat(int n, int r, int p, Map<Integer, Long> map) {
        if (n<r) return 0;
        if (r == 0) return 1;

        return ((map.get(n) * modularMultiplicativeInverse(map.get(r), p)) % p *
                (modularMultiplicativeInverse(map.get(n-r), p)) % p) % p;
    }

    static long fac(int n, int mod){
        if(n <= 1) return 1;
        return ((long) n %mod * fac(n-1, mod))%mod;
    }

}

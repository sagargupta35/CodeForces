import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            int [] arr = ai(n);
            List<Integer> factors = getFactors(n);
            int cnt = 0;
            for(int j : factors){
                int [] all = new int [j];
                int s1 = 0;
                for(int i = 0; i<j; i++){
                    int cur = n/j;
                    int [] temp = new int [cur];
                    for(int k = i, s2 = 0; k<n; k+=j, s2++){
                        temp[s2] = arr[k];
                    }
                    int min = aMin(temp);
                    for(int k = 0; k<cur; k++){
                        temp[k] -= min;
                    }
                    int gcd = gcd(temp);
                    all[s1++] = gcd;
                }
                int gcd = gcd(all);
                if(gcd > 1) cnt++;
                else if(gcd == 0) cnt++;
            }
            System.out.println(cnt);
        }
        out.close();
    }

    static List<Integer> getFactors(int n){
        int root = (int) Math.sqrt(n);
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i<=root; i++){
            if(n%i == 0){
                res.add(i);
                if((n/i) != i){
                    res.add(n/i);
                }
            }
        }
        return res;
    }

    static int gcd(int [] arr){
        int gcd = arr[0];
        for(int i = 1; i<arr.length; i++){
            gcd = (int) gcd(gcd, arr[i]);
        }
        return gcd;
    }

    static boolean isOdd(long n){
        return (n&1) == 1;
    }

    static class FenwickTreePURQ {
        int [] bit;
        int n;
        public FenwickTreePURQ(int n){
            this.n = n;
            bit = new int[n];
        }

        public FenwickTreePURQ(int [] arr){
            n = arr.length;
            bit = new int [n];
            for(int i = 0; i<n; i++){
                bit[i] += arr[i];
                int r = i | (i+1);
                if(r < n) bit[r] += bit[i];
            }
        }

        void add(int i, int delta){ // adding delta to arr[i]
            while (i < n){
                bit[i] += delta;
                i = i | (i+1);
            }
        }

        private long sum(int r){ // computes sum from 0 to r
            long res = 0;
            while (r >= 0){
                res += bit[r];
                r = (r&(r+1))-1;
            }
            return res;
        }

        long sum(int l, int r) {
            if(l == 0) return sum(r);
            return sum(r)-sum(l-1);
        }
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

    static long getNc2(int n){
        if((n&1) == 0) return (long)n/2 * (n-1);
        return (long)n * (n-1)/2;
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

    static int aMax(int [] arr){
        int a = Integer.MIN_VALUE;
        for(int i = 0; i<arr.length; i++) a = Math.max(a, arr[i]);
        return a;
    }

    static int aMin(int [] arr){
        int a = Integer.MAX_VALUE;
        for(int i = 0; i<arr.length; i++) a = Math.min(a, arr[i]);
        return a;
    }

    static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    static int mod = (int) 1e9 + 7;

    static long lcm(long u, long v) {
        return (u / gcd(u, v)) * v;
    }

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

    static boolean isPerfectSquare(long n){
        long k = (long) Math.sqrt(n);
        return (k*k) == n;
    }

    public static List<Integer> findFirstNPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        int num = 2;

        while (primes.size() < n) {
            if (isPrime(num)) {
                primes.add(num);
            }
            num++;
        }

        return primes;
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

    static long modDivide(long a, long b, long m){
        long inv = modularMultiplicativeInverse(b, m);
        if(inv == -1){
            return -1;
        }
        a %= m;
        return (inv * a)%m;
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

    static int [] fenwickTree(List<Pair<Integer, Integer>> list, int n, int x){
        int [] res = new int [n];
        for(var pair: list){
            int i = pair.f;
            int j = pair.s;
            res[i] += x;
            if(j != n-1) res[j+1] -= x;
        }
        for(int i = 1; i<n; i++){
            res[i] += res[i-1];
        }
        return res;
    }

    static <T> void mergeSort(T [] arr, int l, int r, Comparator<T> c){
        if(l < r){
            int mid = l + (r-l)/2;
            mergeSort(arr, l, mid, c);
            mergeSort(arr, mid+1, r, c);
            merge(arr, l, mid, r, c);
        }
    }

    static <T> void merge(T [] arr, int l,int m, int r, Comparator<T> c){
        int n1 = m-l+1; //length of first array
        int n2 = r-m; //length of second array

        Object[] left = new Object[n1];
        Object[] right = new Object[n2];

        System.arraycopy(arr, l, left, 0, n1);
        for(int i = 0; i<n2; i++) right[i] = arr[m+1+i];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2){
            if(c.compare((T) left[i], (T) right[j]) <= 0){
                arr[k++] = (T) left[i++];
            } else{
                arr[k++] = (T) right[j++];
            }
        }

        while (i < n1){
            arr[k++] = (T) left[i++];
        }

        while (j < n2){
            arr[k++] = (T) right[j++];
        }
    }

    static class Graph{
        int vertices;
        List<Integer> [] edges;
        public Graph(int vertices){
            this.vertices = vertices;
            edges = new ArrayList [vertices];
            Arrays.setAll(edges, i -> new ArrayList<>());
        }

        void addEdge(int st, int end, boolean undirected){
            edges[st].add(end);
            if(undirected) edges[end].add(st);
        }

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

    static Map<Integer, Integer> primeFactorise(int [] sieve, int n){
        Map<Integer, Integer> res = new HashMap<>();
        while (n > 1){
            int j = sieve[n];
            res.merge(j, 1, Integer::sum);
            n /= j;
        }
        return res;
    }

    static long lcmOfArray(int [] arr, int max){
        int [] sieve = sieve(max);
        long ans = 1;
        Map<Integer, Integer> primes = new HashMap<>();
        for (int j : arr) {
            Map<Integer, Integer> cur = primeFactorise(sieve, j);
            for (var entry : cur.entrySet()) {
                primes.merge(entry.getKey(), entry.getValue(), Math::max);
            }
        }
        for(var entry: primes.entrySet()){
            ans = (ans * pow(entry.getKey(), entry.getValue()))%mod;
        }
        return ans;
    }

    static long fac(long n, int mod){
        if(n == 0) return 1;
        long res = 1;
        while (n > 0){
            res = (res*n)%mod;
            n--;
        }
        return res;
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
}

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        long k = in.nextLong();
        int lo = 10, hi = 500, ans = -1;
        while (lo <= hi){
            int mid = (lo + hi)/2;
            long prod = binarySearch(mid);
            if(prod >= k){
                ans = mid;
                hi = mid-1;
            } else lo = mid+1;
        }
        StringBuilder sbr = new StringBuilder();
        String s = "codeforces";
        for (int i = 0; i < 10; i++) {
            int cur = ans / 10;
            if(i < ans%10) cur++;
            sbr.append(Character.toString(s.charAt(i)).repeat(cur));
        }
        System.out.println(sbr);
    }

    static long binarySearch(int n){
        long each = n/10;
        long prod = 1;
        for (int i = 0; i < 10; i++) {
            long cur = each;
            if(i < n%10) cur++;
            prod *= cur;
        }
        return prod;
    }

    static List<Integer> [] getTree(int n){
        List<Integer> [] adj = new ArrayList [n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt()-1;
            int v = in.nextInt()-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        return adj;
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

    static long nCrModPFermat(int n, int r, int p, long [] fac) {
        if (n<r) return 0;
        if (r == 0) return 1;

        return ((fac[n] * modularMultiplicativeInverse(fac[r], p)) % p *
                (modularMultiplicativeInverse(fac[n - r], p)) % p) % p;
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

    static int mod = (int)1e9 + 7;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

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
            return java.lang.Integer.parseInt(next());
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
}

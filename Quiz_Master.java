import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] arr = ai(n);
            Arrays.sort(arr);
            int [] fr = new int [m+1];
            int cnt = m;
            int lo = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                var facs = getFactors(arr[i]);
                for(int j : facs){
                    if(j <= m){
                        fr[j]++;
                        if(fr[j] == 1) cnt--;
                    }
                }
                if(cnt == 0){
                    while (cnt == 0){
                        var fs = getFactors(arr[lo]);
                        for(int j : fs){
                            if(j <= m){
                                fr[j]--;
                                if(fr[j] == 0) cnt++;
                            }
                        }
                        lo++;
                    }
                    if(lo > 0){
                        min = Math.min(min, arr[i]-arr[lo-1]);
                    }
                }
            }
            if(min == Integer.MAX_VALUE){
                System.out.println(-1);
                continue;
            }
            System.out.println(min);
        }
    }

    static List<Integer> getFactors(int n){
        List<Integer> res = new ArrayList<>();
        int root = (int)Math.sqrt(n);
        for (int i = 1; i <= root; i++) {
            if(n%i == 0){
                res.add(i);
                if(i != n/i){
                    res.add(n/i);
                }
            }
        }
        return res;
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

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long m = in.nextLong();
            List<Pair<Integer, Pair<Integer, Integer>>> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new Pair<>(in.nextInt(), new Pair<>(in.nextInt(), in.nextInt())));
            }
            if(check(list, 0, m - list.get(0).f, m + list.get(0).f)){
                System.out.println("YES");
            } else System.out.println("NO");
        }
    }

    static boolean check(List<Pair<Integer, Pair<Integer, Integer>>> list, int st, long l, long h){
        if(st == list.size()) return true;
        var pp = list.get(st);
        int t = pp.f; var p = pp.s;
        if(l > p.s || h < p.f) return false;
        l = Math.max(l, p.f); h = Math.min(h, p.s);
        if(st < list.size()-1){
            l -= list.get(st+1).f-t;
            h += list.get(st+1).f-t;
        }
        return check(list, st+1, l, h);
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
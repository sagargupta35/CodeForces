import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            List<Integer> [] adj = new ArrayList [n];
            Arrays.setAll(adj, i -> new ArrayList<>());
            for (int i = 0; i < n - 1; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                adj[u-1].add(v-1);
                adj[v-1].add(u-1);
            }
            int max = maxDepth(adj, 0, new boolean[n], 0);
            int [] depth = new int [max+2];
            dfs(adj, 0, new boolean[n], 0, depth);
            int cost = n;
            for (int i = 1; i < depth.length; i++) {
                depth[i] += depth[i-1];
                cost = Math.min(cost, n - depth[i]);
            }
            System.out.println(cost);
        }
    }

    static int dfs(List<Integer> [] adj, int st, boolean [] vis, int level, int [] depth){
        vis[st] = true;
        int max = level;
        for(int j : adj[st]){
            if(!vis[j]){
                max = Math.max(max, dfs(adj, j, vis, level+1, depth));
            }
        }
        depth[level]++;
        depth[max+1]--;
        return max;
    }

    static int maxDepth(List<Integer> [] adj, int st, boolean [] vis, int level){
        vis[st] = true;
        int max = level;
        for(int j : adj[st]){
            if(!vis[j]){
                max = Math.max(max, maxDepth(adj, j, vis, level+1));
            }
        }
        return max;
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

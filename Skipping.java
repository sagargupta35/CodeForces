import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            int[] b = ai(n);
            List<Pair<Integer, Integer>> [] adj = new ArrayList [n];
            Arrays.setAll(adj, i -> new ArrayList<>());
            for (int i = 0; i < n; i++) {
                if(i > 0) adj[i].add(new Pair<>(0, i-1));
                if(b[i]-1 > i) adj[i].add(new Pair<>(arr[i], b[i]-1));
            }
            long [] pre = new long [n];
            pre[0] = arr[0];
            for (int i = 1; i < n; i++) {
                pre[i] += pre[i-1] + arr[i];
            }
            long [] dist = dijkstra(adj, 0);
            long res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, pre[i] - dist[i]);
            }
            System.out.println(res);
        }
    }

    static long [] dijkstra(List<Pair<Integer, Integer>> [] adj, int src){
        int n = adj.length;
        long [] dist = new long [n];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.f));
        dist[src] = 0;
        pq.add(new Pair<>(0L, src));
        while (!pq.isEmpty()){
            var p = pq.poll();
            for(var edge : adj[p.s]){
                long cost = dist[p.s] + edge.f;
                if(dist[edge.s] > cost){
                    dist[edge.s] = cost;
                    pq.add(new Pair<>(cost, edge.s));
                }
            }
        }
        return dist;
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

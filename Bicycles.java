import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            List<List<Pair<Integer, Long>>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());;
            }
            for (int i = 0; i < m; i++) {
                int u = in.nextInt()-1;
                int v = in.nextInt()-1;
                long w = in.nextLong();
                adj.get(u).add(new Pair<>(v, w));;
                adj.get(v).add(new Pair<>(u, w));
            }
            long [] cycles = al(n);
            long [][] weights = new long[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(weights[i], Long.MAX_VALUE);
            }
            for (int i = 0; i < n; i++) {
                weights[i][i] = 0L;
            }
            dijkstra(adj, cycles, weights);
            long min = Long.MAX_VALUE;
            for (int i = 0; i < n - 1; i++) {
                min = Math.min(min, weights[n-1][i]);
            }
            System.out.println(min);
        }
    }

    static void dijkstra(List<List<Pair<Integer, Long>>> adj, long [] cycles, long [][] weights){
        PriorityQueue<Pair<Integer, Pair<Integer, Long>>> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.s.s));
        pq.add(new Pair<>(0, new Pair<>(0, 0L)));
        while (!pq.isEmpty()){
            var p = pq.poll();
            int u = p.f, cycle = p.s.f;
            long reachWeight = p.s.s;
            if(cycles[u] < cycles[cycle]) cycle = u;
            for(var edge : adj.get(u)){
                int v = edge.f;
                long w = edge.s;
                long travelWeight = reachWeight + w*cycles[cycle];
                if(travelWeight < weights[v][cycle]){
                    weights[v][cycle] = travelWeight;
                    pq.add(new Pair<>(v, new Pair<>(cycle, travelWeight)));
                }
            }
        }
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

    static class Pair<T, U>  {
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

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Pair<?,?>)) return false;
            Pair<T, U> pair = (Pair<T, U>) obj;
            return f.equals(pair.f) && s.equals(pair.s);
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

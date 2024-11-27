import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            var adj= getTree(n);
            Set<Integer> set = new HashSet<>();
            set.add(0);
            Queue<Pair<Integer, Integer>> q = new LinkedList<>();
            q.add(new Pair<>(a, 0));
            boolean [] vis = new boolean[n];
            while (!q.isEmpty()){
                var p = q.poll();
                for(var edge: adj[p.f]){
                    int v = edge.f; int w = edge.s;
                    if(!vis[v]){
                        if(v != b){
                            q.add(new Pair<>(v, p.s ^ w));
                            set.add(p.s ^ w);
                            vis[v] = true;
                        }
                    }
                }
            }
            Arrays.fill(vis, false);
            boolean flag = false;
            vis[b] = true;
            q.add(new Pair<>(b, 0));
            while (!q.isEmpty()){
                var p = q.poll();
                for(var edge : adj[p.f]){
                    int v = edge.f; int w = edge.s;
                    if(!vis[v]){
                        vis[v] = true;
                        if(set.contains(p.s ^ w)){
                            flag = true;
                            break;
                        }
                        q.add(new Pair<>(v, p.s ^ w));
                    }
                }
            }
            if(flag){
                System.out.println("YES");
            } else System.out.println("NO");
        }
    }



    static List<Pair<Integer, Integer>>[] getTree(int n) {
        List<Pair<Integer, Integer>>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            adj[u].add(new Pair<>(v, w));
            adj[v].add(new Pair<>(u, w));
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

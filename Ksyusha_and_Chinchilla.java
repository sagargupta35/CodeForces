import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            List<Pair<Integer, Integer>> [] adj = new ArrayList [n];
            Arrays.setAll(adj, i -> new ArrayList<>());
            for (int i = 0; i < n-1; i++) {
                int u = in.nextInt()-1;
                int v = in.nextInt()-1;
                adj[u].add(new Pair<>((i+1), v));
                adj[v].add(new Pair<>(i+1, u));
            }
            List<Integer> res = new ArrayList<>(n);
            int j = dfs(adj, 0, -1, res);
            if(j != 0){
                System.out.println(-1);
                continue;
            }
            System.out.println(res.size());
            for(int k : res) System.out.print(k + " ");
            System.out.println();
        }
    }

    static int dfs(List<Pair<Integer, Integer>> [] adj, int cur, int par, List<Integer> res){
        int sum = 0;
        for(var p : adj[cur]){
            int num = p.f;
            int v = p.s;
            if(v != par){
                int j = dfs(adj, v, cur, res);
                if(j == -1) return -1;
                if(j == 0){
                    res.add(num);
                } else sum += j;
            }
        }
        if(sum > 2) return -1;
        return (sum+1)%3;
    }

    static class Dsu{

        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        public Dsu(int n) {
            for (int i = 0; i < n; i++) {
                size.add(1);
                parent.add(i);
            }
        }

        int findPar(int u){
            if(parent.get(u) == u) return u;
            int par = findPar(parent.get(u));
            parent.set(u, par);
            return par;
        }

        void union(int u, int v){
            int up = findPar(u);
            int vp = findPar(v);
            if(up == vp) return;
            if(size.get(up) < size.get(vp)){
                parent.set(up, vp);
                size.set(vp, size.get(up) + size.get(vp));
            } else{
                parent.set(vp, up);
                size.set(up, size.get(up) + size.get(vp));
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

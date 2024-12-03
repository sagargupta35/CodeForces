import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            List<Edge> [] adj = new ArrayList [n];
            Arrays.setAll(adj, i -> new ArrayList<>());
            for (int i = 1; i < n; i++) {
                adj[in.nextInt()-1].add(new Edge(i, in.nextInt(), in.nextInt()));
            }
            long [] arr = new long [n+1];
            int [] res = new int [n];
            dfs(adj, 0, arr, 0, 0, res);
            for (int i = 1; i < n; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
        }
    }

    static void dfs(List<Edge> [] adj, int v, long [] arr, int max, long a, int [] res){
        res[v] = binarySearch(arr, max, a);
        for(var e : adj[v]){
            arr[max+1] = arr[max] + e.b;
            dfs(adj, e.val, arr, max+1, a + e.a, res);
        }
    }

    static int binarySearch(long [] arr, int max, long target){
        int lo = 0, hi = max, ans = 0;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(arr[mid] <= target){
                ans = mid;
                lo = mid+1;
            } else hi = mid-1;
        }
        return ans;
    }

    static class Edge{
        int val, a, b;

        public Edge(int val, int a, int b) {
            this.val = val;
            this.a = a;
            this.b = b;
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

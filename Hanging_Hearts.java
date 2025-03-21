import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] arr = ai(n - 1);
        List<Integer> [] adj = new ArrayList [n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < n-1; i++) {
            adj[arr[i]-1].add(i+1);
        }
        int [][] dp = new int [n][2];
        dfs(adj, dp, 0);
        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }

    static void dfs(List<Integer> [] adj, int [][] dp, int cur){
        int max = 0;
        int sum = 0;
        for(int v : adj[cur]){
            dfs(adj, dp, v);
            max = Math.max(dp[v][0], max);
            sum += Math.max(dp[v][0], dp[v][1]);
        }
        dp[cur][0] = max+1;
        dp[cur][1] = sum;
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

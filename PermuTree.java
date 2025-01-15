import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        List<Integer> [] adj = new ArrayList [n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            int p = in.nextInt();
            adj[p-1].add(i);;
        }
        long [] sum = new long [1];
        dfs(adj, 0, sum);
        System.out.println(sum[0]);
    }

    static int dfs(List<Integer> [] adj, int u, long [] sum){
        int tot = 0;
        List<Integer> list = new ArrayList<>();
        for(int v : adj[u]){
            int j = dfs(adj, v, sum);
            tot += j;
            list.add(j);
        }
        sum[0] += f(list);
        return tot+1;
    }

    static long f(List<Integer> list){
        int sum = 0;
        for (var size : list) {
            sum += size;
        }
        int n = list.size();
        long [] dp = new long [sum+1];
        for (int i = 0; i <= sum; i++) {
            dp[i] = (long) i *(sum-i);
        }
        for (int i = n - 1; i >= 0; i--) {
            long [] temp = new long [sum+1];
            for (int k = 0; k <= sum; k++) {
                temp[k] = dp[k];
                if(k + list.get(i) <= sum){
                    temp[k] = Math.max(temp[k], dp[k+list.get(i)]);
                }
            }
            dp = temp;
        }
        return dp[0];
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

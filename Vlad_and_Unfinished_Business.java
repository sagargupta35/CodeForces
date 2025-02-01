
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            in.nextLine();
            int n = in.nextInt();
            int k = in.nextInt();
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            boolean [] vis = new boolean[n];
            for (int i = 0; i < k; i++) {
                vis[in.nextInt()-1] = true;
            }
            List<Integer> [] adj = new ArrayList [n];
            Arrays.setAll(adj, i -> new ArrayList<>());
            for (int i = 0; i < n-1; i++) {
                int u = in.nextInt()-1;
                int v = in.nextInt()-1;
                adj[u].add(v);
                adj[v].add(u);
            }
            int [] sum = new int [1];
            add(adj, vis, x, -1, sum);
            subtract(adj, x, -1, sum, y, vis);
            sum[0]++;
            System.out.println(sum[0]);
        }
    }

    static boolean subtract(List<Integer> [] adj, int cur, int par, int [] sum, int y, boolean [] vis){
        if(cur == y){
            if(vis[y]) sum[0]--;
            else sum[0]++;
            return true;
        }
        boolean flag = false;
        for(int j : adj[cur]){
            if(j != par){
                flag = subtract(adj, j, cur, sum, y, vis);
            }
            if(flag) break;
        }
        if(flag){
          if(vis[cur]) sum[0]--;
          else sum[0]++;
        }
        return flag;
    }

    static boolean add(List<Integer> [] adj, boolean [] vis, int cur, int par, int [] sum){
        boolean flag = vis[cur];
        for(int j : adj[cur]){
            if(j != par){
                boolean temp = add(adj, vis, j, cur,sum);
                if(temp) sum[0] += 2;
                flag |= temp;
            }
        }
        vis[cur] = flag;
        return flag;
    }


    static void reverse(int [] arr){
        int n = arr.length;
        for (int i = 0; i < n/2; i++) {
            int temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
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

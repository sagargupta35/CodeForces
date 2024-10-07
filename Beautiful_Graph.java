import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        int mod = 998244353;
        while(t-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            List<Integer> [] adj = new ArrayList [n];
            Arrays.setAll(adj, i -> new ArrayList<>());
            for (int i = 0; i < m; i++) {
                int u = in.nextInt()-1;
                int v = in.nextInt()-1;
                adj[u].add(v);
                adj[v].add(u);
            }
            boolean [] vis = new boolean[n];
            Arrays.fill(vis, false);
            long sum = 1;
            for (int i = 0; i < n; i++) {
                if(!vis[i]){
                    long cur = getSum(adj, vis, mod, i);
                    if(cur == -1){
                        sum = 0;
                        break;
                    }
                    sum = (sum * cur)%mod;
                }
            }
            System.out.println(sum);
        }
    }

    private static long getSum(List<Integer>[] adj, boolean[] vis, int mod, int st) {
        int l1 = 1, l2 = 0;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> odd = new HashSet<>();
        Set<Integer> even = new HashSet<>();
        q.add(st);
        odd.add(st);
        vis[st] = true;
        boolean flag = false;
        while (!q.isEmpty()){
            Queue<Integer> cur = new LinkedList<>();
            while (!q.isEmpty()){
                int u = q.poll();
                for(int j : adj[u]){
                    if(!vis[j]){
                        cur.add(j);
                        vis[j] = true;
                        if(flag) odd.add(j);
                        else even.add(j);
                    } else{
                        if(flag){
                            if(even.contains(j)) return -1;
                        } else{
                            if(odd.contains(j)) return -1;
                        }
                    }
                }
            }
            if(flag){
                l1 += cur.size();
            } else{
                l2 += cur.size();
            }
            flag = !flag;
            q = cur;
        }
        return (pow(2, l1, mod) + pow(2, l2, mod))% mod;
    }

    static long pow(long a, long b, long mod){
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


    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec){
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

    }

    static int mod = (int) 1e9 + 7;

    static int aMax(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int j : arr) max = Math.max(max,j);
        return max;
    }

    static int aMin(int [] arr){
        int min = Integer.MAX_VALUE;
        for(int j : arr) min = Math.min(min, j);
        return min;
    }

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

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
            return Integer.parseInt(next());
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

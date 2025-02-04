
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt()-1;
        List<Integer> [] adj = new ArrayList [n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = in.nextInt()-1;
            int v = in.nextInt()-1;
            adj[u].add(v);
        }
        List<Integer> [] parents = new ArrayList [n];
        Arrays.setAll(parents, i -> new ArrayList<>());
        boolean flag = false;
        int [] times = new int [n];
        Arrays.fill(times, Integer.MAX_VALUE);
        int time = 0;
        for(int j : adj[s]){
            times[s] = time;
            flag = dfs(adj, j, times, time++, parents, s);
            if(flag) break;
        }
        if(!flag){
            System.out.println("Impossible");
            return;
        }
        System.out.println("Possible");
        int t = -1;
        for(int j = 0; j<n; j++){
            if(parents[j].size() > 1){
                t = j;
                break;
            }
        }
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();
        p1.add(t);
        p2.add(t);
        for (int i = 0; i < 2; i++) {
            int cur = parents[t].get(i);
            while (cur != s){
                if(i == 0){
                    p1.add(cur);;
                } else {
                    p2.add(cur);
                }
                cur = parents[cur].get(0);
            }
        }
        p1.add(s);
        p2.add(s);
        System.out.println(p1.size());
        for (int i = p1.size() - 1; i >= 0; i--) {
            System.out.print((p1.get(i)+1) + " ");
        }
        System.out.println();
        System.out.println(p2.size());
        for (int i = p2.size() - 1; i >= 0; i--) {
            System.out.print((p2.get(i)+1) + " ");
        }
        System.out.println();
    }

    static boolean dfs(List<Integer> [] adj, int cur, int [] times, int time, List<Integer> [] parents, int par){
        if(times[cur] < time){
            parents[cur].add(par);
            return true;
        } else if(times[cur] == time) return false;
        boolean flag = false;
        parents[cur].add(par);
        times[cur] = time;
        for(int j : adj[cur]){
            flag = dfs(adj, j, times, time, parents, cur);
            if(flag) break;
        }
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

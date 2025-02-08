
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            List<Integer> [] adj = new ArrayList [n];
            Arrays.setAll(adj, i -> new ArrayList<>());
            for (int i = 0; i < n; i++) {
                int k = in.nextInt();
                while (k-- > 0){
                    int v = in.nextInt()-1;
                    adj[i].add(v);
                }
            }
            int [] time = new int [n];
            int [] tt = new int [n];
            int max = 1;
            for (int i = 0; i < n; i++) {
                Stack<Integer> st = new Stack<>();
                if(time[i] == 0){
                    boolean hasCycle = hasCycle(adj, i, time, st);
                    if(hasCycle){
                        max = -1;
                        break;
                    }
                    int cur = findTimes(st, adj, tt);
                    max = Math.max(max, cur);
                }
            }
            System.out.println(max);
        }
    }

    static boolean hasCycle(List<Integer> [] adj, int cur, int [] time, Stack<Integer> stack){
        if(time[cur] == 1) return true;
        if(time[cur] == 2) return false;
        time[cur] = 1;
        boolean flag = false;
        for(int j : adj[cur]){
            flag = hasCycle(adj, j, time, stack);
            if(flag) break;
        }
        time[cur] = 2;
        stack.push(cur);
        return flag;
    }

    static int findTimes(Stack<Integer> st, List<Integer> [] adj, int [] time){
        Stack<Integer> rst = new Stack<>();
        while (!st.isEmpty()){
            rst.push(st.pop());
        }
        st = rst;
        int max = 1;
        while (!st.isEmpty()){
            int cur = st.pop();
            time[cur] = 1;
            for(int v : adj[cur]){
                if(v < cur){
                    time[cur] = Math.max(time[cur], time[v]);
                } else time[cur] = Math.max(time[cur], time[v]+1);
            }
            max = Math.max(max, time[cur]);
        }
        return max;
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

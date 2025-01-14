import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            int [] arr = new int [n+1];
            for (int i = 1; i <= n; i++) {
                arr[i] = in.nextInt();
            }
            int c = in.nextInt();
            NavigableMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 1; i <= n; i++) {
                map.put(arr[i], i);
            }
            int [] res = new int [n+1];
            res[0] = Integer.MAX_VALUE;
            int i = 0;
            while (c > 0 && !map.isEmpty() && i < n){
                var cur = map.pollFirstEntry();
                int k = cur.getKey(), v = cur.getValue();
                if(v < i) continue;
                int diff = arr[v] - arr[i];
                int take = c/ diff;
                if(res[i] < take){
                    res[v] = res[i];
                    c -= res[i]*diff;
                    res[i] = 0;
                } else{
                    res[i] -= take;
                    res[v] = take;
                    c %= diff;
                }
                i = v;
            }
            for (int j = n-1; j > 0; j--) {
                res[j] += res[j+1];
            }
            for (int j = 1; j <= n; j++) {
                System.out.print(res[j] + " ");
            }
            System.out.println();
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

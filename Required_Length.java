import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        long x = in.nextLong();
        if(String.valueOf(x).length() == n){
            System.out.println(0);
            return;
        } else if(n == 1){
            boolean flag = false;
            for (long i = 1; i < x; i *= 10) {
                if((n/i)%10 == 0){
                    flag = true;
                    break;
                }
            }
            if(flag) System.out.println(1);
            else System.out.println(-1);
            return;
        }
        int ops = getMin(x, n, new TreeMap<>());
        if(ops < Integer.MAX_VALUE) System.out.println(ops);
        else System.out.println(-1);
    }

    static int getMin(long x, long n, NavigableMap<Long, Integer> map){
        String s = String.valueOf(x);
        if(s.length() == n) return 0;
        if(x < 0) return Integer.MAX_VALUE;
        if(s.length() > n) return Integer.MAX_VALUE;
        if(map.containsKey(x)) return map.get(x);
        int ops = Integer.MAX_VALUE;
        boolean [] vis = new boolean [10];
        for (long i = 1; i < x; i *= 10) {
            vis[(int) ((x/i)%10)] = true;
        }
        for (int i = 2; i < 10; i++) {
            if(vis[i]){
                int cur = getMin(x*i, n, map);
                if(cur < Integer.MAX_VALUE){
                    ops = Math.min(ops, cur+1);
                }
            }
        }
        map.put(x, ops);
        return ops;
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

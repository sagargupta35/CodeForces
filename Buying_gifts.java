import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            Pair<Integer, Integer> [] arr = new Pair[n];
            Arrays.setAll(arr, i -> new Pair<>(in.nextInt(), in.nextInt()));
            Arrays.sort(arr, Comparator.comparingInt(p -> p.f));
            NavigableMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < n-1; i++) {
                map.merge(arr[i].s, 1, Integer::sum);
            }
            int cur = -1;
            int res = Integer.MAX_VALUE;
            for (int i = n - 2; i >= 0; i--) {
                if(map.get(arr[i].s) == 1){
                    map.remove(arr[i].s);
                } else map.put(arr[i].s, map.get(arr[i].s)-1);
                cur = Math.max(cur, arr[i+1].s);
                res = Math.min(res, Math.abs(arr[i].f - cur));
                if(cur == arr[i].f) break;
                if(cur < arr[i].f){
                    Integer lower = map.floorKey(arr[i].f);
                    if(lower != null){
                        if(lower > cur){
                            res = Math.min(res, arr[i].f - lower);
                        }
                    }
                    Integer higher = map.ceilingKey(arr[i].f);
                    if(higher != null){
                        res = Math.min(res, higher - arr[i].f);
                    }
                }
            }
            for (int i = 0; i < n - 1; i++) {
                res = Math.min(res, Math.abs(arr[n-1].f - arr[i].s));
            }
            System.out.println(res);
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

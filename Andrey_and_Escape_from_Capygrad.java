import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> [] arr = new Pair[n];
            for (int i = 0; i < n; i++) {
                int l = in.nextInt();
                int r = in.nextInt();
                int a = in.nextInt();
                int b = in.nextInt();
                arr[i] = new Pair<>(new Pair<>(l, r), new Pair<>(a, b));
            }
            Arrays.sort(arr, Comparator.comparingInt(p -> p.f.f));
            NavigableMap<Pair<Integer, Integer>, Integer> map = new TreeMap<>(Comparator.comparingInt(p -> p.f));
            int l = arr[0].f.f, r = arr[0].f.s;
            int max = arr[0].s.s;
            for (int i = 1; i < n; i++) {
                if(max < arr[i].f.f){
                    map.put(new Pair<>(l, arr[i].f.f-1), max);
                    l = arr[i].f.f;
                    r = arr[i].s.s;
                    max = arr[i].s.s;
                    continue;
                }
                max = Math.max(max, arr[i].s.s);
                r = Math.max(r, arr[i].f.s);
            }
            map.put(new Pair<>(l, r), max);
            int q = in.nextInt();
            while (q-- > 0){
                int x = in.nextInt();
                var entry = map.floorEntry(new Pair<>(x, -1));
                if(entry == null){
                    System.out.println(x);
                    continue;
                }
                System.out.print(Math.max(x, entry.getValue()) + " ");;
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

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        long t = in.nextLong();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.putIfAbsent(arr[i], new ArrayList<>());
                map.get(arr[i]).add(i);
            }
            int max = 1;
            int a = arr[0];
            int l = 0, r = 0;
            for(var entry : map.entrySet()){
                int k = entry.getKey();
                var list = entry.getValue();
                var cur = check(list);
                if(cur[0] > max){
                    max = cur[0];
                    a = k;
                    l = cur[1];
                    r = cur[2];
                }
            }
            System.out.println(a + " " + (l+1) + " " + (r+1));
        }
    }

    static int [] check(List<Integer> list){
        int max = 1, ll = 0, l = 0, r = 0, cur = 1;
        while (cur < list.size()){
            int len = list.get(cur) - list.get(l) + 1;
            int plus = cur - l + 1;
            if(2*plus <= len){
                while (2*plus <= len){
                    l++;
                    len = list.get(cur) - list.get(l) + 1;
                    plus = cur - l + 1;
                }
            }
            int diff = 2*plus - len;
            if(diff > max){
                max = diff;
                ll = list.get(l); r = list.get(cur);
            }
            cur++;
        }
        return new int [] {max, ll, r};
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

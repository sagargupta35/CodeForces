import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int p = in.nextInt();
            int[] arr = ai(n);
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(arr[i]);
            }
            int max = p-1;
            while (set.contains(max)) max--;
            if(max == -1){
                System.out.println(0);
                continue;
            }
            int min = arr[n-1];
            while (set.contains(min)) min--;
            if(min == -1){
                System.out.println(max - arr[n-1]);
                continue;
            }
            long sum = p - arr[n-1];
            int temp = arr[n-1];
            add(arr, p, set);
            while (set.contains(temp)) temp--;
            if(temp == -1){
                System.out.println(sum);
                continue;
            }
            System.out.println(sum + temp);
        }
    }

    static void add(int [] arr, int p, Set<Integer> set){
        int mod = 1;
        int n = arr.length;
        arr[n-1] = p-1;
        for (int i = n - 1; i >= 0; i--) {
            int cur = arr[i] + mod;
            if(cur >= p){
                arr[i] = 0;
            } else{
                arr[i] = cur;
                mod = 0;
            }
            set.add(arr[i]);
        }
        if(mod == 1) set.add(mod);
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

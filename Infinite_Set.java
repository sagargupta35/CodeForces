
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int p = in.nextInt();
        long[] arr = al(n);
        Arrays.sort(arr);
        NavigableSet<Long> set = new TreeSet<>();
        long [] dp = new long [p + 5];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (2 + dp[i-1] + dp[i-2])%mod;
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if(!exists(arr[i], set)){
                int log = (int)(Math.log(arr[i])/Math.log(2));
                if(log < p){
                    int diff = p - log - 1;
                    sum = (sum + dp[diff] + 1)%mod;
                } else break;
                set.add(arr[i]);
            }
        }
        System.out.println(sum);
    }

    static boolean exists(long j, NavigableSet<Long> set){
        if(j == 0) return false;
        if(set.contains(j)) return true;
        boolean flag = false;
        if((j&1) == 1) flag = exists(j/2, set);
        if(j%4 == 0) flag |= exists(j/4, set);
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

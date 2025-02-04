
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            int[] b = ai(n);
            if(n == 1){
                System.out.println(0);
                continue;
            }
            int res = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                res += arr[i]*arr[i] + b[i]*b[i];
                sum += Math.max(arr[i], b[i]);
            }
            res *= (n-1);
            int [][] dp = new int [n][sum + 5];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            res += 2 * Math.min(f(arr, b, n-2, dp, arr[n-1], b[n-1]), f(arr, b, n-2, dp, b[n-1], arr[n-1]));
            System.out.println(res);
        }
    }

    static int f(int [] a, int [] b, int cur, int [][] dp, int sum1, int sum2){
        if(cur < 0) return 0;
        if(dp[cur][sum1] != -1) return dp[cur][sum1];
        int noSwap = a[cur]*sum1 + b[cur]*sum2 + f(a, b, cur-1, dp, sum1+a[cur], sum2+b[cur]);
        int swap = b[cur]*sum1 + a[cur]*sum2 + f(a, b, cur-1, dp, sum1+b[cur], sum2+a[cur]);
        return dp[cur][sum1] = Math.min(swap, noSwap);
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

import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int w = in.nextInt();
            int f = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int [n+1];
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                arr[i] = in.nextInt();
                sum += arr[i];
            }
            long [] dp = new long [sum+1];
            for (int i = 0; i <= sum; i++) {
                int wc = i;
                int fc = sum - i;
                dp[i] = Math.max((wc + w - 1)/ w, (fc + f-1)/f);
            }
            for (int i = n ; i > 0; i--) {
                long [] temp = new long [sum+1];
                Arrays.fill(temp, Long.MAX_VALUE);
                for (int wc = 0; wc <= sum; wc++) {
                    // kill with fire
                    temp[wc] = dp[wc];
                    // kill with water
                    if(wc+arr[i] <= sum){
                        temp[wc] = Math.min(dp[wc+arr[i]], temp[wc]);
                    }
                }
                dp = temp;
            }
            System.out.println(dp[0]);
        }
    }

//    static int f(int [] arr, int i, int wc, int fc, int w, int f, int [][] time){
//        int n = arr.length;
//        if(i == n){
//            return Math.max((wc + w - 1)/w,  (fc + f - 1)/f);
//        }
//        if(time[i][wc] != -1) return time[i][wc];
//        long cf = f(arr, i+1, wc, fc+arr[i], w, f, time);
//        long cw = f(arr, i+1, wc+arr[i], fc, w, f, time);
//        return time[i][wc] = Math.min(cf, cw);
//    }

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

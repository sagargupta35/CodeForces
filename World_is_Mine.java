import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            Arrays.sort(arr);
            int cnt = 0, pre = arr[0];
            List<Integer> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                if(arr[i] == pre) cnt++;
                else{
                    list.add(cnt);
                    cnt = 1;
                    pre = arr[i];
                }
            }
            list.add(cnt);
            n = list.size();
            int [][] dp = new int [n+1][n+1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][k] = dp[i-1][k];
                    if(k > 0){
                        if(dp[i-1][k-1] != Integer.MAX_VALUE){
                            int s = list.get(i-1) + dp[i-1][k-1];
                            if(s <= i - k){
                                dp[i][k] = Math.min(dp[i][k], s);
                            }
                        }
                    }
                }
            }
            int res = 0;
            for (int i = n; i >= 0; i--) {
                if(dp[n][i] != Integer.MAX_VALUE){
                    res = i;
                    break;
                }
            }
            System.out.println(n-res);
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

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            String [] arr = new String[n];
            Arrays.setAll(arr, i -> in.nextLine());
            int [][] dp = new int [n+1][5];
            char [] narek = new char[] {'n', 'a', 'r', 'e', 'k'};
            for (int i = 0; i < 5; i++) {
                dp[n][i] = -i;
            }
            Function<Character, Boolean> fun = c -> switch (c){
                case 'n', 'a', 'r', 'e', 'k' -> true;
                default -> false;
            };
            for (int i = n - 1; i >= 0; i--) {
                System.arraycopy(dp[i+1], 0, dp[i], 0, 5);
                String s = arr[i];
                for (int k = 0; k < 5; k++) {
                    int sn = 0, sc = 0;
                    int next = k;
                    int cnt = k;
                    for (int j = 0; j < s.length(); j++) {
                        if(s.charAt(j) == narek[next]){
                            cnt++;
                            next = (next+1)%5;
                            sn += (cnt/5)*5;
                            cnt %= 5;
                        } else if(fun.apply(s.charAt(j))) sc++;
                    }
                    sn += dp[i+1][next];
                    dp[i][k] = Math.max(dp[i][k], sn-sc);
                }
            }
            System.out.println(Math.max(0, dp[0][0]));
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

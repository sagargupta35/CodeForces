import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            String s = in.nextLine();
            int n = s.length();
            int [][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -2);
            }
            findWinner(s, 0, n-1, dp, 0, '.');
            switch (dp[0][n-1]){
                case 0 -> System.out.println("Draw");
                case 1 -> System.out.println("Bob");
                default -> System.out.println("Alice");
            }

        }
    }

    static int findWinner(String s, int i, int j, int [][] dp, int chance, char prev){
        if(chance == 0 && dp[i][j] != -2){
            return dp[i][j];
        }
        if(i == j){
            char cur = s.charAt(i);
            if(cur < prev) return dp[i][i] = 1;
            if(cur == prev) return dp[i][i] = 0;
            return dp[i][i] = -1;
        }
        if(chance == 0){
            char first = s.charAt(i);
            int winner = findWinner(s, i+1, j, dp, 1, first);
            if(winner == -1){
                return dp[i][j] = -1;
            }
            boolean draw = winner == 0;
            char last = s.charAt(j);
            winner = findWinner(s, i, j-1, dp, 1, last);
            if(winner == -1){
                return dp[i][j] = -1;
            }
            draw |= winner == 0;
            if(draw) return dp[i][j] = 0;
            return dp[i][j] = 1;
        } else{
            char first = s.charAt(i);
            int winner = findWinner(s, i+1, j, dp, 0, first);
            if(winner == 1) return 1;

            boolean draw = winner == 0 && prev == s.charAt(i);

            char last = s.charAt(j);
            winner = findWinner(s, i, j-1, dp, 0, last);
            if(winner == 1) return 1;

            draw |= winner == 0 && prev == s.charAt(j);

            if(draw) return 0;
            return -1;
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

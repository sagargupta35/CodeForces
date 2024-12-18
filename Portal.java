import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            String [] arr = new String[n];
            Arrays.setAll(arr, i -> in.nextLine());
            int res = Integer.MAX_VALUE;
            int [][] col = new int [n][m];
            for (int j = 0; j < m; j++) {
                col[0][j] = arr[0].charAt(j) - '0';
                for (int i = 1; i < n; i++) {
                    col[i][j] += col[i-1][j] + arr[i].charAt(j)-'0';
                }
            }
            int [][] row = new int [n][m];
            for (int i = 0; i < n; i++) {
                row[i][0] = arr[i].charAt(0) - '0';
                for (int j = 1; j < m; j++) {
                    row[i][j] += row[i][j-1] + arr[i].charAt(j) - '0';
                }
            }
            int [][] sub = new int [n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    sub[i][j] = arr[i].charAt(j) - '0';
                    if(i > 0) sub[i][j] += sub[i-1][j];
                    if(j > 0) sub[i][j] += sub[i][j-1];
                    if(i > 0 && j > 0) sub[i][j] -= sub[i-1][j-1];
                }
            }
            for (int i1 = 1; i1 < n - 3; i1++) {
                for (int i2 = i1 + 2; i2 < n - 1; i2++) {
                    int suf = Integer.MAX_VALUE;
                    for (int j = m - 4; j >= 0; j--) {
                        int cur = (i2 - i1 + 1) - (col[i2][j+3] - col[i1-1][j+3]);
                        int up = (j+3) - row[i1-1][j+2];
                        int bot = (j+3) - row[i2+1][j+2];
                        int mid = sub[i2][j+2] - sub[i1-1][j+2];
                        cur += up + bot + mid;
                        suf = Math.min(suf, cur);
                        int cost = (i2 - i1 + 1) - (col[i2][j] - col[i1-1][j]);
                        int cls = sub[i2][j] - sub[i1-1][j];
                        int cup = (j+1) - (row[i1-1][j]);
                        int cbot = (j+1) - (row[i2+1][j]);
                        cost -= (cls + cup + cbot);
                        res = Math.min(res, cost + suf);
                    }
                }
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

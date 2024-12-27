  import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            String s = in.nextLine();
            int [][] pre = new int [n][];
            for (int i = 0, x = 0, min = 0, max = 0; i < n; i++) {
                if(s.charAt(i) == '+') x++;
                else x--;
                min = Math.min(min, x); max = Math.max(max, x);
                pre[i] = new int [] {x, min, max};
            }
            int [][] suf = new int [n][2];
            for (int i = n - 1,  min = 0, max = 0; i >= 0; i--) {
                if(i == n-1){
                    if(s.charAt(i) == '+'){
                        suf[n-1][0] = suf[n-1][1] = 1;
                    } else suf[n-1][0] = suf[n-1][1] = -1;
                    continue;
                }
                if(s.charAt(i) == '+'){
                    suf[i][0] = suf[i+1][0] + 1;
                    suf[i][1] = Math.max(1, suf[i+1][1] + 1);
                } else{
                    suf[i][0] = Math.min(-1, suf[i+1][0]-1);
                    suf[i][1] = suf[i+1][1] - 1;
                }
            }
            while (m-- > 0){
                int l = in.nextInt()-1;
                int r = in.nextInt()-1;
                if(l == 0 && r == n-1){
                    System.out.println(1);
                } else if(l == 0){
                    int neg = Math.abs(Math.min(0, suf[r+1][0]));
                    int pos = Math.max(0, suf[r+1][1]);
                    System.out.println(neg + pos + 1);
                } else if(r == n-1){
                    int neg = Math.abs(Math.min(0, pre[l-1][1]));
                    int pos = Math.max(0, pre[l-1][2]);
                    System.out.println(neg + pos + 1);
                } else{
                    int neg = pre[l-1][1];
                    int pos = pre[l-1][2];
                    int cur = pre[l-1][0];
                    neg = Math.abs(Math.min(0, Math.min(neg, suf[r+1][0] + cur)));
                    pos = Math.max(0, Math.max(pos, suf[r+1][1] + cur));
                    System.out.println(neg + pos + 1);
                }
            }
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

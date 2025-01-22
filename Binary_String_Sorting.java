import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            long cost = (long) 1e12;
            String s = in.nextLine();
            int n = s.length();
            int z = 0, o = 0;
            for(int i = 0; i<n; i++){
                if(s.charAt(i) == '0') z++;
                else o++;
            }
            if(o == 0 || z == 0){
                System.out.println(0);
                continue;
            }
            long res = Math.min(z*(cost+1), o*(cost+1));
            z = 0;
            int cnt = 0;
            for (int i = n - 1; i >= 0; i--) {
                if(s.charAt(i) == '0') cnt++;
                else{
                    o--;
                    long temp = z*(cost+1) + o*(cost+1) + cnt*cost;
                    res = Math.min(temp, res);
                    z += cnt;
                    cnt = 0;
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

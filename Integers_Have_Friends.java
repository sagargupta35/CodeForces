import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long[] arr = al(n);
            if(n == 1){
                System.out.println(1);
                continue;
            }
            long [] diff = new long [n-1];
            for (int i = 0; i < n-1; i++) {
                diff[i] = Math.abs(arr[i] - arr[i+1]);
            }
            long [][] sparse = buildSparse(diff);
            int max = 0;
            for (int i = 0; i < diff.length; i++) {
                int lo = i, hi = diff.length-1;
                while (lo <= hi){
                    int mid = lo + (hi - lo)/2;
                    if(query(sparse, i, mid) > 1){
                        max = Math.max(max, mid - i + 1);
                        lo = mid+1;
                    } else hi = mid-1;
                }
            }
            System.out.println(max+1);
        }
    }

    static long query(long [][] sparse, int i, int j){
        int len = j - i + 1;
//        System.out.println(len);
        int log = (int) (Math.log(len)/Math.log(2));
//        System.out.println(log);
        return gcd(sparse[i][log], sparse[j - (1 << log) + 1][log]);
    }

    static long [][] buildSparse(long [] diff){
        int n = diff.length;
        int log = (int)(Math.log(n)/Math.log(2));
        long [][] sparse = new long [n][log+1];
        for (int i = 0; i < n; i++) {
            sparse[i][0] = diff[i];
        }
        for (int k = 1; k <= log; k++) {
            for (int i = 0; i + (1 << k) - 1 < n; i++) {
                sparse[i][k] = gcd(sparse[i][k-1], sparse[i + (1 << (k-1))][k-1]);
            }
        }
        return sparse;
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

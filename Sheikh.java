import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] arr = new int[n + 1];
            long[] pre = new long[n + 1];
            int[] xor = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = in.nextInt();
                pre[i] += arr[i] + pre[i - 1];
                xor[i] = xor[i - 1] ^ arr[i];
            }
            int l = in.nextInt();
            int r = in.nextInt();
            int left = l, right = r;
            long max = pre[r] - pre[l - 1] - (xor[r] ^ xor[l - 1]);
            for (int i = l; i <= r; i++) {
                int lo = l, hi = i;
                while (lo <= hi){
                    int mid = lo + (hi - lo)/2;
                    long curSum = pre[i] - pre[mid-1];
                    long curXor = xor[i] ^ xor[mid-1];
                    long cur = curSum - curXor;
                    if(cur == max){
                        if(right - left > i - mid){
                            left = mid;
                            right = i;
                        }
                        lo = mid+1;
                    } else{
                        hi = mid-1;
                    }
                }
            }
            System.out.println(left + " " + right);

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

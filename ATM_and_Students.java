
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long s = in.nextLong();
            long[] arr = al(n);
            long[] pre = new long[n];
            pre[0] = arr[0];
            for (int i = 1; i < n; i++) {
                pre[i] += pre[i - 1] + arr[i];
            }
            int k = 32 - Integer.numberOfLeadingZeros(n);
            long[][] st = new long[n][k];
            for (int i = 0; i < n; i++) {
                st[i][0] = pre[i];
            }
            for (int j = 1; j < k; j++) {
                for (int i = 0; i + (1 << (j - 1)) < n; i++) {
                    st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
            int max = 0, left = -1, right = -1;
            for (int i = 0; i < n; i++) {
                long bound = -s;
                if (i > 0) bound += pre[i - 1];
                int lo = i, hi = n - 1;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    long min = query(st, lo, mid);
                    if (min >= bound) {
                        int cur = mid - i + 1;
                        if (cur > max) {
                            max = cur;
                            left = i;
                            right = mid;
                        }
                        lo = mid + 1;
                    } else hi = mid-1;
                }
            }
            if (max == 0) System.out.println(-1);
            else System.out.println((left + 1) + " " + (right + 1));
        }
    }

    static long query(long [][] st, int l, int r){
        int len = r - l + 1;
        int k = 31 - Integer.numberOfLeadingZeros(len);
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
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

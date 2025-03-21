 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            long l = in.nextLong();
            long r = in.nextLong();
            int i = in.nextInt();
            long k = in.nextLong();
            long res = rangeXor(l, r);
            long left = -1, right = -1;
            long lo = 0, hi = (1L << (60 - i))-1;
            while (lo <= hi){
                long mid = lo + (hi - lo)/2;
                long cur = mid * (1L << i) + k;
                if(cur >= l){
                    left = mid;
                    hi = mid-1;
                } else lo = mid+1;
            }
            lo = 0; hi = (1L << (60 - i))-1;
            while (lo <= hi){
                long mid = lo + (hi - lo)/2;
                long cur = mid * (1L << i) + k;
                if(cur <= r){
                    right = mid;
                    lo = mid+1;
                } else{
                    hi = mid-1;
                }
            }
            int cnt = (int) (((right - left) + 1)&1);
            if(cnt == 1){
                res ^= k;
            }
            if(left == -1 || right == -1){
                System.out.println(res);
                continue;
            }
            long x = rangeXor(left, right);
            x *= (1L << i);
            System.out.println(res^x);
        }
    }

    public static long xorUpto(long x) {
        if (x % 4 == 0) return x;
        if (x % 4 == 1) return 1;
        if (x % 4 == 2) return x + 1;
        return 0;
    }

    public static long rangeXor(long l, long r) {
        if(l == 0) return xorUpto(r);
        return xorUpto(r) ^ xorUpto(l - 1);
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(f, pair.f) && Objects.equals(s, pair.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(f, s);
        }

        @Override
        public String toString() {
            return f + " " + s;
        }
    }

    static int mod = 998244353;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }


    static void reverse(int [] arr, int i, int j){
        while (i <= j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
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
            return Integer.parseInt(next());
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

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }
}

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        if(m < n-1){
            System.out.println("Impossible");
            return;
        }
        int temp = m - (n-1);
        for (int i = 2; i < n && temp > 0; i++) {
            for (int j = i + 1; j <= n && temp > 0; j++) {
                if(gcd(i, j) == 1) temp--;
            }
        }
        if(temp > 0){
            System.out.println("Impossible");
            return;
        }
        System.out.println("Possible");
        temp = m;
        for (int i = 1; i < n && temp > 0; i++) {
            for (int j = i + 1; j <= n && temp > 0; j++) {
                if(gcd(i, j) == 1){
                    System.out.println(i + " " + j);
                    temp--;
                }
            }
        }
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec){
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

    }

    static int mod = (int) 1e9 + 7;

    static int aMax(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int j : arr) max = Math.max(max,j);
        return max;
    }

    static int aMin(int [] arr){
        int min = Integer.MAX_VALUE;
        for(int j : arr) min = Math.min(min, j);
        return min;
    }

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

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
}

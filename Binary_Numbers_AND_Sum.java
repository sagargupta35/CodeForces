import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int mod = 998244353;
        int n = in.nextInt();
        int m = in.nextInt();
        String a = in.nextLine();
        String b = in.nextLine();
        long s1 = 0, s2 = 0, sum = 0, cur = 1;
        int i = n-1, j = m-1;
        while (i >= 0 && j >= 0){
            if(a.charAt(i) == '1'){
                s1 = (s1 + cur)%mod;
            }
            if(b.charAt(j) == '1'){
                sum = (sum + s1 + s2)%mod;
                s2 = (s2 + s1)%mod;
                s1 = 0;
            }
            i--; j--;
            cur = (cur * 2)%mod;
        }
        s2 = (s2 + s1)%mod;
        while (j >= 0){
            if(b.charAt(j) == '1'){
                sum = (sum + s2)%mod;
            }
            j--;
        }
        System.out.println(sum);
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

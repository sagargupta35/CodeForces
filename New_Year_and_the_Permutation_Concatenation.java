import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        long [] fac = new long[n+1];
        int mod = 998244353;
        fac[0] = 1;
        for (int i = 1; i <= n; i++) {
            fac[i] = (fac[i-1]*i)%mod;
        }
        long sum = fac[n];
        int temp = n;
        long prev = n;
        while (temp > 1){
            long cur = fac[temp-1];
            cur--;
            if(cur < 0) cur += mod;
            sum = (sum + (prev*cur))%mod;
            temp--;
            prev = (prev * temp)%mod;
        }
        System.out.println(sum);
    }

    static boolean sol(String [][] arr, boolean [][] flags, String prev){
        if(prev.length() == arr.length){
            return check(arr, flags);
        }
        if(arr[prev.length()][0].startsWith(prev)){
            flags[prev.length()][0] = true;
            if(sol(arr, flags, arr[prev.length()][0])){
                return true;
            }
            flags[prev.length()][0] = false;
        }
        if(arr[prev.length()][1].startsWith(prev)){
            flags[prev.length()][1] = true;
            if(sol(arr, flags, arr[prev.length()][1])){
                return true;
            }
            flags[prev.length()][1] = false;
        }
        return false;
    }

    static boolean check(String [][] arr, boolean [][] flags){
        String prev = flags[0][0] ? arr[0][1] : arr[0][0];
        for (int i = 1; i < arr.length; i++) {
            if(flags[i][0]){
                if(!arr[i][1].endsWith(prev)) return false;
                prev = arr[i][1];
            } else{
                if(!arr[i][0].endsWith(prev)) return false;
                prev = arr[i][0];
            }
        }
        return true;
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

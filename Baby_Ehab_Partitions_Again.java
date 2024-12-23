import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] arr = ai(n);
        int sum = 0;
        for(int j : arr) sum += j;
        if((sum&1) == 1){
            System.out.println(0);
        } else{
            if(!checkSubsequence(arr, sum/2, 0, new Boolean[n][sum+1])){
                System.out.println(0);
                return;
            }
            System.out.println(1);
            int odd = -1;
            while (odd == -1){
                odd = div(arr);
            }
            System.out.println(odd+1);
        }
    }

    static int div(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            if((arr[i]&1) == 1) return i;
            else arr[i] /= 2;
        }
        return -1;
    }

    private static boolean checkSubsequence(int[] arr, int target, int index, Boolean [][] dp) {
        if (target == 0) {
            return true;
        }
        if (index >= arr.length || target < 0) {
            return false;
        }
        if(dp[index][target] != null) return dp[index][target];
        return dp[index][target] = checkSubsequence(arr, target - arr[index], index + 1, dp) ||
                checkSubsequence(arr, target, index + 1, dp);
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

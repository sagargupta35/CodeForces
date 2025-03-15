 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            Arrays.sort(arr);
            int [][] dp = new int [n][500];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            System.out.println(find(arr, dp, 1, 0));
        }
    }

    static int find(int [] arr, int [][] dp, int t, int i){
        int n = arr.length;
        if(i >= n) return 0;
        if(dp[i][t] != -1) return dp[i][t];
        if(t > arr[i]){
            return dp[i][t] = t - arr[i] + find(arr, dp, t+1, i+1);
        }
        int sum = Integer.MAX_VALUE;
        for (int j = arr[i]; j >= t; j--) {
            int cur = (arr[i] - j) + find(arr, dp, j+1, i+1);
            sum = Math.min(sum, cur);
            dp[i][j] = sum;
        }
        return dp[i][t];
    }

    static boolean is(long [] arr, long sum){
        for (long l : arr) {
            if (l == sum) return true;
        }
        return false;
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
}

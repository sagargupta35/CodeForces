import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int n = in.nextInt();
        long[] arr = al(n);
        Arrays.sort(arr);
        long [][] dp = new long [n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n; i++) {
            findMin(arr, i, i, dp);
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[i][i]);
        }
        System.out.println(min);
    }

    static long findMin(long [] arr, int i, int j, long [][] dp){
        int n = arr.length;
        if(dp[i][j] != -1) return dp[i][j];
        if(i == 0 && j == n-1){
            return dp[i][j] = arr[n-1] - arr[0];
        }
        long cur = arr[j] - arr[i];
        long left = Long.MAX_VALUE;
        long right = Long.MAX_VALUE;
        if(i > 0){
            left = findMin(arr, i-1, j, dp);
        }
        if(j < n-1){
            right = findMin(arr, i, j+1, dp);
        }
        return dp[i][j] = cur + Math.min(left, right);
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

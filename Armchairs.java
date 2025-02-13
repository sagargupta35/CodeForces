import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] arr = ai(n);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i] == 1) cnt++;
        }
        if(cnt == 0){
            System.out.println(0);
            return;
        }
        int [] pos = new int [cnt];
        int [] emp = new int [n-cnt];
        for (int i = 0, j = 0, k = 0; i < n; i++) {
            if(arr[i] == 1) pos[j++] = i;
            else emp[k++] = i;
        }
        long [][] dp = new long [cnt][n-cnt];
        for (int i = 0; i < cnt; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(findSum(pos, emp, dp, 0, 0));
    }

    static long findSum(int [] pos, int [] emp, long [][] dp, int i, int j){
        int n = pos.length, m = emp.length;
        if(i >= n) return 0;
        if(j >= m) return Long.MAX_VALUE;
        if(dp[i][j] != -1) return dp[i][j];
        long cost =  findSum(pos, emp, dp, i, j+1);
        long cost2 = findSum(pos, emp, dp, i+1, j+1);
        if(cost2 != Long.MAX_VALUE){
            cost2 += Math.abs(emp[j] - pos[i]);
        }
        return dp[i][j] = Math.min(cost, cost2);
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

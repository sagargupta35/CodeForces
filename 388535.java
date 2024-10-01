import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0){
            int l = in.nextInt();
            int r = in.nextInt();
            int [] arr = ai(r+1);
            int max = aMax(arr);
            int res = 0;
            int cur = 1;
            while (cur <= max){
                int or = getCount(r, cur);
                int pr = getCount(arr, cur);
                if(or != pr) res ^= cur;
                cur *= 2;
            }
            System.out.println(res);
        }
    }

    static int getCount(int r, int bit){
        int cnt = 0;
        for (int i = 0; i <= r; i++) {
            if((i&bit) > 0) cnt++;
        }
        return cnt;
    }

    static int getCount(int [] arr, int bit){
        int cnt = 0;
        for(int j : arr){
            if((j & bit) > 0) cnt++;
        }
        return cnt;
    }

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

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            int k = in.nextInt();
            String a = in.nextLine();
            String b = in.nextLine();
            int [] f = new int [26];
            for (int i = 0; i < n; i++) {
               f[a.charAt(i)-'a']++;
               f[b.charAt(i)-'a']--;
            }
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if(f[i] != 0){
                    flag = false;
                    break;
                }
            }
            if(!flag){
                System.out.println("NO");
                continue;
            }
            for (int i = Math.max(0, n - k);  i < Math.min(n, k) && flag; i++) {
                flag = (a.charAt(i) == b.charAt(i));
            }
            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }
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

import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long k = in.nextLong();
            if(n <= 60){
                if((1L << (n-1)) < k){
                    System.out.println(-1);
                    continue;
                }
            }
            int [] arr = new int [n];
            Arrays.setAll(arr, i -> i+1);
            while (k > 1){
                long cur = 1;
                int pos= n-1;
                for (int i = n - 1; i >= 0; i--) {
                    cur = (1L << (n-i-1));
                    if(cur >= k){
                        pos = i;
                        break;
                    }
                }
                int i = pos;
                while (i < n && k > 1){
                    k -= cur/2;
                    cur /= 2;
                    i++;
                    if((cur/2) >= k) break;
                }
                reverse(arr, pos, i);
            }
            for(int j : arr) System.out.print(j + " ");
            System.out.println();
        }
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

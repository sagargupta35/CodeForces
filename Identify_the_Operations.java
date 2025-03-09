 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = ai(n);
            int[] b = ai(k);
            if(n == 1){
                System.out.println(0);
                continue;
            }
            boolean [] no = new boolean[n+1];
            int [] idx = new int [n+1];
            for (int i = 0; i < k; i++) {
                no[b[i]] = true;
            }
            for (int i = 0; i < n; i++) {
                idx[arr[i]] = i;
            }
            long sum = 1;
            for (int i = 0; i < k; i++) {
                int cur = idx[b[i]];
                if(cur == 0){
                    if(no[arr[1]]){
                        sum = 0;
                        break;
                    }
                    no[arr[0]] = false;
                } else if(cur == n-1){
                    if(no[arr[n-2]]){
                        sum = 0;
                        break;
                    }
                    no[arr[n-1]] = false;
                } else{
                    if(no[arr[cur-1]] && no[arr[cur+1]]){
                        sum = 0;
                        break;
                    }
                    if(!no[arr[cur-1]] && !no[arr[cur+1]]){
                        sum = (sum*2)%998244353;
                    }
                    no[arr[cur]] = false;
                }
            }
            System.out.println(sum);
        }
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

import java.io.*;
import java.util.*;

public class CodeChef3 {


    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int [][] arr = new int [n][2];
            int [][] cost = new int [n][k+1];
            for (int i = 0; i < n; i++) {
                arr[i][0] = in.nextInt();
                arr[i][1] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                int h = Math.min(arr[i][0], arr[i][1]);
                int w = arr[i][0] + arr[i][1] - h;
                fill(h, w, cost[i]);
            }
            int [] dp = new int [k+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = n - 1; i >= 0; i--) {
                int [] temp = new int [k+1];
                Arrays.fill(temp, Integer.MAX_VALUE);
                for (int prev = 0; prev <= k; prev++) {
                    for (int fill = 0; fill <= prev; fill++) {
                        int next = prev - fill;
                        if(dp[next] != Integer.MAX_VALUE){
                            if(cost[i][fill] != Integer.MAX_VALUE){
                                int cur = cost[i][fill] + dp[next];
                                temp[prev] = Math.min(temp[prev], cur);
                            }
                        }
                    }
                }
                dp = temp;
            }
            if(dp[k] != Integer.MAX_VALUE) System.out.println(dp[k]);
            else System.out.println(-1);
        }

    }

    static void fill(int h, int w, int [] arr){
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (int i = 0; i <= w; i++) { // no.of cols to fill
            for (int j = 0; j <= h; j++) { //no.of rows to fill
                int points = i + j;
                int cost = i*h;
                cost += (w - i)*j;
                int cur = Math.min(points, arr.length-1);
                arr[cur] = Math.min(arr[cur], cost);
            }
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i] = Math.min(arr[i], arr[i+1]);
        }
    }

    static class Triplet{
        int a, b, c;

        public Triplet(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c;
        }
    }

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(f, pair.f) && Objects.equals(s, pair.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(f, s);
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

import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            Pair<Integer, Integer> [] arr = new Pair[m];
            Arrays.setAll(arr, i -> new Pair<>(in.nextInt(), in.nextInt()));
            Arrays.sort(arr, Comparator.comparingInt(p -> p.f));
            int min = arr[0].f;
            int [] pm = new int [m];
            for (int i = 0; i < m; i++) {
                pm[i] = arr[i].s;
                if(i > 0){
                    pm[i] = Math.max(pm[i], pm[i-1]);
                }
            }
            int [] feed = new int [n+5];
            for (int i = 0; i < m; i++) {
                feed[arr[i].f]++;
                feed[arr[i].s+1]--;
            }
            for (int i = 1; i <= n; i++) {
                feed[i] += feed[i-1];
            }
            int [] dp = new int [n+5];
            dp[n] = feed[n];
            for (int i = n - 1; i >= min; i--) {
                dp[i] = dp[i+1];
                int next = pm[binarySearch(arr, i)];
                if(next+1 > i){
                    dp[i] = Math.max(dp[i], dp[next+1] + feed[i]);
                }
            }
            System.out.println(dp[min]);
        }
    }

    static int binarySearch(Pair<Integer, Integer> [] arr, int target){
        int lo = 0, hi = arr.length-1;
        int ans = 0;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid].f <= target){
                ans = mid;
                lo = mid+1;
            } else hi = mid-1;
        }
        return ans;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static long lcm(long u, long v) {
        return (u / gcd(u, v)) * v;
    }

    static class Triplet <T, U, V>{
        T a;
        U b;
        V c;

        public Triplet(T a, U b, V c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) obj;
            return Objects.equals(a, triplet.a) && Objects.equals(b, triplet.b) &&
                    Objects.equals(c, triplet.c);
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

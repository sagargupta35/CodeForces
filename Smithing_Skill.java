import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = ai(n);
        int[] b = ai(n);
        int max = -1;
        for(int j : arr) max = Math.max(max, j);
        int [] c = ai(m);
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int diff = arr[i] - b[i];
            if(!map.containsKey(diff)){
                map.put(diff, arr[i]);
            } else{
                map.put(diff, Math.min(arr[i], map.get(diff)));
            }
        }
        long sum = 0;
        int diff = map.firstKey();
        int min = map.firstEntry().getValue();
        for (int i = 0; i < m; i++) {
            if(c[i] > max){
                int k = (c[i] - min)/diff;
                sum += k*2L;
                c[i] -= k*diff;
                if(c[i] >= min){
                    c[i] -= diff;
                    sum += 2;
                }
            }
        }
        int [] best = new int [max+1];
        Arrays.fill(best, Integer.MAX_VALUE);
        for(int i = 0; i<n; i++){
            best[arr[i]] = Math.min(best[arr[i]], arr[i] - b[i]);
        }
        for (int i = 1; i <= max; i++) {
            best[i] = Math.min(best[i], best[i-1]);
        }
        int [] ans = new int [max+1];
        for (int i = 1; i <= max; i++) {
            if(best[i] <= i){
                ans[i] = 2 + ans[i - best[i]];
            }
        }
        for(int j : c){
            sum += ans[j];
        }
        System.out.println(sum);
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

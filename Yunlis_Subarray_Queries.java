import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int q = in.nextInt();
            int[] arr = ai(n);
            Arrays.setAll(arr, i -> arr[i]-i);
            Map<Integer, Integer> map = new HashMap<>();
            NavigableMap<Integer, Integer> f = new TreeMap<>();
            int [] res = new int [n];
            for (int i = 0; i < n; i++) {
                int temp = map.getOrDefault(arr[i], 0);
                if(temp > 0){
                    int tt = f.get(temp);
                    if(tt > 0){
                        f.put(temp, tt-1);
                    } else f.remove(temp);
                }
                map.merge(arr[i], 1, Integer::sum);
                f.merge(map.get(arr[i]), 1, Integer::sum);
                if(i >= k-1){
                    res[i-k+1] = f.lastKey();
                    int prev = map.get(arr[i-k+1]);
                    int pp = f.get(prev);
                    if(pp > 1){
                        f.put(prev, pp-1);
                    } else f.remove(prev);
                    if(prev > 1){
                        f.merge(prev-1, 1, Integer::sum);
                        map.put(arr[i-k+1], prev-1);
                    } else map.remove(arr[i-k+1]);
                }
            }
            while (q-- > 0){
                int l = in.nextInt()-1;
                int r = in.nextInt()-1;
                System.out.println(k - res[l]);
            }
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

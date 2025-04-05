import java.io.*;
import java.util.*;

public class CodeChef3 {


    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            long[] arr = al(n);
            long[] b = al(n);
            int ops = 1;
            Arrays.setAll(arr, i -> b[i] - arr[i]);
            PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.s));
            int cnt = 0;
            int i = n-1;
            boolean [] vis = new boolean[n];
            while (cnt < n){
                if(arr[i] >= 0){
                    if(!vis[i]){
                        vis[i] = true;
                        cnt++;
                        pq.add(new Pair<>(arr[i], i));
                    }
                } else{
                    while (!pq.isEmpty()){
                        var p = pq.poll();
                        if(p.f <= -arr[i]){
                            arr[i] += p.f;
                        } else{
                            pq.add(new Pair<>(p.f+arr[i], p.s));
                            arr[i] = 0;
                        }
                        if(arr[i] == 0){
                            int cur = p.s - i;
                            if(cur < 0) cur += n;
                            ops = Math.max(ops, cur+1);
                            vis[i] = true;
                            cnt++;
                            break;
                        }
                    }
                }
                i--;
                if(i < 0) i += n;
            }
            System.out.println(ops);
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

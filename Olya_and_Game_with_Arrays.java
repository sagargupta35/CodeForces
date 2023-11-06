import java.util.*;
import java.io.*;
import java.util.function.*;

public class CodeChef2{
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

    record Pair(int first, int second) implements Comparable<Pair> {
        @Override
        public int compareTo(Pair o) {
            return second - o.second == 0 ? first - o.first : second - o.second;
        }
    }

    public static void main (String[] args) throws java.lang.Exception {
        FastReader in = new FastReader();
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            Pair [] arr = new Pair[n];
            int min = Integer.MAX_VALUE;
            for(int i = 0; i<n; i++){
                int m = in.nextInt();
                int first = Integer.MAX_VALUE;
                int second = Integer.MAX_VALUE;
                while(m-- > 0){
                    int k = in.nextInt();
                    min = Math.min(k, min);
                    if(k <= first){
                        second = first;
                        first = k;
                    } else if(k < second) second = k;
                }
                arr[i] = new Pair(first, second);
            }
            Arrays.sort(arr);
            long sum = min;
            for(int i = 1; i<n; i++){
                sum += arr[i].second;
            }
            out.println(sum);
        }
        out.close();
    }
}

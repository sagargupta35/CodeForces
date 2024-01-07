import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.regex.Pattern;

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

    static class Graph{
        List<Integer> [] connections;
        int vertices;
        public Graph(int vertices){
            this.vertices = vertices;
            connections = new ArrayList [vertices];
        }

        void addEdge(int st, int end){
            if(connections[st] != null) connections[st].add(end);
            else connections[st] = new ArrayList<>(List.of(end));
            if(connections[end] != null) connections[end].add(st);
            else connections[end] = new ArrayList<>(List.of(st));
        }
    }

    public static void main (String[] args) throws java.lang.Exception {
        FastReader in = new FastReader();
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        int mod = (int) Math.pow(10, 9) + 7;
        while(t-- > 0){
            int n = in.nextInt();
            long [] arr = new long [n];
            arr[0] = in.nextInt();
            long min = arr[0], max = arr[0];
            for(int i= 1; i<n; i++){
                int k = in.nextInt();
                min = Math.min(k, min);
                max = Math.max(max, k);
                arr[i] = arr[i-1]+k;
            }
            max -= min;
            if(max == 0){
                out.println(0);
            } else {
                List<Integer> facs = factors(n);
                facs.remove(Integer.valueOf(1));
                facs.remove(Integer.valueOf(n));
                for (int fac : facs) {
                    long min1 = arr[fac-1];
                    long max1 = min1;
                    for (int j = fac * 2 - 1; j < n; j += fac) {
                        long b = arr[j] - arr[j - fac];
                        min1 = Math.min(min1, b);
                        max1 = Math.max(max1, b);
                    }
                    max = Math.max(max, max1-min1);
                }
                out.println(max);
            }
        }
        out.close();
    }

    public static List<Integer> factors(int number) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                factors.add(i);

                // If the factors are distinct, add the other factor
                if (i != number / i) {
                    factors.add(number / i);
                }
            }
        }

        return factors;
    }
}

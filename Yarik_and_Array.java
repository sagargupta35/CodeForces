import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.function.*;
 
class CodeForces {
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
 
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int [] arr = new int [n];
            for(int i = 0; i<n; i++) arr[i] = in.nextInt();
            long sum = Long.MIN_VALUE;
            BiFunction<Integer, Integer, Boolean> fun = (k, j) ->  (((k^j)&1) == 1);
            int i = 1;
            long temp = arr[0];
            while(i < n){
                while(i < n && fun.apply(arr[i], arr[i-1])){
                    sum = Math.max(sum, temp);
                    temp = Math.max(arr[i], temp+arr[i]);
                    i++;
                }
                sum = Math.max(sum, temp);
                if(i < n) {
                    temp = arr[i++];
                }
            }
            sum = Math.max(sum, temp);
            out.println(sum);
        }
        out.close();
    }
}

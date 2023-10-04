import java.util.*;
import java.io.*;
 
public class B_Chips_on_the_Board {
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
 
    public static void main (String[] args) throws java.lang.Exception
    {
        FastReader in = new FastReader();
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        while(t-- > 0){
             int n = in.nextInt();
             long a = 0;
             long b = 0;
             long min_a = Long.MAX_VALUE;
             long min_b = min_a;
             for(int i = 0; i<n; i++){
                 int k = in.nextInt();
                 min_a = Math.min(min_a, k);
                 a += k;
             }
            for(int i = 0; i<n; i++){
                int k = in.nextInt();
                min_b = Math.min(min_b, k);
                b += k;
            }
            min_a *= n;
            min_b *= n;
            out.println(Math.min(a +min_b, b +min_a));
        }
        out.close();
    }
}
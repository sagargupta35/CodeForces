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
 
    public static void main (String[] args) throws java.lang.Exception {
        FastReader in = new FastReader();
        FastWriter out =  new FastWriter();
        int t = in.nextInt();
        Function<Integer, Integer> fun = a -> -(a/3 + ((a%3 == 1) ? 1 : 0));
        while(t-- > 0) {
            int n = in.nextInt();
            long [] arr = new long [n];
            for(int i = 0; i<n; i++) arr[i] = in.nextLong();
            int odd = (int)arr[0]%2, sub = 0;
            long pre = arr[0];
            out.print(arr[0] + " ");
            for(int i = 1; i<n; i++){
                if(arr[i] % 2 == 1) odd++;
                int k = fun.apply(odd);
                pre = pre + (k-sub) + arr[i];
                sub = k;
                out.print(pre + " ");
            }
            out.println("");
        }
        out.close();
    }
}

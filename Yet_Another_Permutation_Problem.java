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
        while(t-- > 0){
            int n = in.nextInt();
            boolean [] arr = new boolean[n+1];
            int i = 1;
            while(i <= n){
                if(!arr[i]){
                    out.print(i + " ");
                    arr[i] = true;
                    int j = i*2;
                    while(j <= n && !arr[j]){
                        out.print(j + " ");
                        arr[j] = true;
                        j *= 2;
                    }
                }
                while(i <= n && arr[i]) i++;
            }
            out.println("");
        }
        out.close();
    }
}

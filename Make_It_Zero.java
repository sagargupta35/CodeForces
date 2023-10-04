import java.util.*;
import java.io.*;
import java.util.function.*;

public class Make_It_Zero {
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
            int [] arr = new int [n];
            for(int i = 0; i<n; i++) arr[i] = in.nextInt();
            if((arr.length & 1) == 0){
                out.println(2);
                out.println(1 + " " + arr.length);
                out.println(1 + " " + arr.length);
            } else{
                out.println(4);
                out.println(1 + " " + arr.length);
                out.println(1 + " " + (arr.length-1));
                out.println(arr.length-1 + " " + arr.length);
                out.println(arr.length-1 + " " + arr.length);
            }
        }
        out.close();
    }

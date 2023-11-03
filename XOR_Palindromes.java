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
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            String s = in.nextLine();
            int odd = 0;
            int even = 0;
            for(int i = 0, j = n-1; i<j; i++, j--){
                if(s.charAt(i) == s.charAt(j)) even++;
                else odd++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<=n; i++){
                int k = i-odd;
                if(k==0) sb.append(1);
                else if(k > 0){
                    if((n&1) == 1){
                        if(k/2 <= even){
                            sb.append(1);
                        }else{
                            sb.append(0);
                        }
                    } else{
                        if((k&1) == 0 && k/2 <= even){
                            sb.append(1);
                        } else{
                            sb.append(0);
                        }
                    }
                } else{
                    sb.append(0);
                }
            }
            out.println(sb);
        }
        out.close();
    }
}

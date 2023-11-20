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
            StringBuilder sbr = new StringBuilder("0" + in.nextLine());
            int j = 1;
            boolean flag = false;
            while(!flag && j < sbr.length()){
                if(sbr.charAt(j) >= '5'){
                    flag = true;
                    sbr.setCharAt(j-1, (char) (sbr.charAt(j-1)+1));
                    sbr.replace(j, sbr.length(), "0".repeat(sbr.length() - j));
                    j--;
                } else j++;
            }
            int i = j;
            if(flag) {
                while (i >= 0) {
                    if (sbr.charAt(i--) >= '5') {
                        while (i >= 0 && sbr.charAt(i) == '9') i--;
                        sbr.replace(i + 1, sbr.length(), "0".repeat(sbr.length() - (i + 1)));
                        sbr.setCharAt(i, (char) (sbr.charAt(i) + 1));
                    }
                }
            }
            System.out.println(sbr.charAt(0) == '0' ? sbr.substring(1) : sbr);
        }
        out.close();
    }

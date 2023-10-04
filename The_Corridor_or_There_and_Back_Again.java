import java.util.*;
import java.io.*;
import java.util.function.*;

public class The_Corridor_or_There_and_Back_Again {
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
            Map<Integer, Integer> map = new HashMap<>();
            while(n-- > 0){
                int d = in.nextInt();
                int s = in.nextInt();
                map.merge(d, s, Math::min);
            }
            int current = 1;
            int max = Integer.MAX_VALUE;
            while(current < max){
                Integer k = map.get(current);
                if(k != null){
                    if(k == 1) break;
                    max = Math.min(current+((k-1)/2), max);
                    if(max > current) current++;
                } else current++;
            }
            out.println(Math.max(1, current));
        }
        out.close();
    }
}

import java.util.*;
import java.io.*;
import java.util.function.*;

public class Balanced_Round {
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
            int k = in.nextInt();
            int [] arr = new int [n];
            for(int i = 0; i<n; i++) arr[i] = in.nextInt();

            Arrays.sort(arr);
            int min = Integer.MAX_VALUE;
            int temp = 0;

            while(temp < n-1 && arr[temp+1] - arr[temp] <= k) temp++;
            int ll = -1, lg = temp;

            for(int i = 0; i<n; i++){
                if(lg == i){
                    min = Math.min(min, n+ll-lg);
                    lg++;
                    while(lg < n-1 && arr[lg+1] - arr[lg] <= k) lg++;
                    ll = i;
                } else{
                    if(lg < n-1) min = Math.min(min, ll+1+n-lg);
                    else min = Math.min(min, ll+1);
                }
            }

            out.println(min);

        }
        out.close();
    }
}

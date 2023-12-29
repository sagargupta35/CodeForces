import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.function.*;

public class CodeChef {
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
    static int mod = (int) (Math.pow(10, 9) + 7);
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            String s = in.nextLine();
            int [] dp = new int [n];
            Function<Character, Boolean> fun = c -> switch (c){
                case 'a', 'e' -> true;
                default -> false;
            };
            process(s, dp, 0, n, fun);
            int i = 0;
            while(i < n){
                int j = dp[i];
                while(i <= j) out.print(s.charAt(i++));
                if(i >= n-1) break;
                out.print(".");
            }
            out.println("");
        }
        out.close();
    }

    public static boolean process(String s, int [] dp, int x, int n, Function<Character, Boolean> fun){
        if(n-x <= 0) return true;
        if(fun.apply(s.charAt(x)) || n-x ==1) return false;
        if(dp[x] != 0) return true;
        if(n-x == 2){
            boolean flag =  !fun.apply(s.charAt(x)) && fun.apply(s.charAt(x+1));
            if(flag){
                dp[x] = n-1;
            }
            return flag;
        }
        if(n-x == 3){
            boolean flag = !fun.apply(s.charAt(x)) && fun.apply(s.charAt(x+1)) && !fun.apply(s.charAt(x+2));
            if(flag) dp[x] = n-1;
            return flag;
        }
        boolean flag1 = false, flag2 = false;
        if(fun.apply(s.charAt(x+1))){
            flag1 = process(s, dp, x+2, n, fun);
        }
        if(!flag1){
            if(!fun.apply(s.charAt(x+2))){
                flag2 = process(s, dp, x+3, n, fun);
                if(flag2) dp[x] = x+2;
            }
        } else{
            dp[x] = x+1;
        }
        return flag1 || flag2;
    }

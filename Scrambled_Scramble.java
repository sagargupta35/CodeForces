import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

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
        String s = in.nextLine();
        int v = 0, c = 0, n = 0, g = 0, y = 0;
        Function<Character, Boolean> fun = c1 -> Set.of('A', 'E', 'I', 'O', 'U').contains(c1);
        for (int i = 0; i < s.length(); i++) {
            if(fun.apply(s.charAt(i))) v++;
            else{
                char ch = s.charAt(i);
                if(ch == 'N') n++;
                else if(ch == 'G') g++;
                else if(ch == 'Y') y++;
                else c++;
            }
        }
        int len = 0;
        for (int ng = 0; ng <= Math.min(n, g); ng++) {
            for (int y1 = 0; y1 <= y; y1++) {
                int cv = v + y1;
                int cc = c + (n+g-ng) + y - y1;
                int ss = Math.min(cv, cc/2);
                int cur = Math.min(2*ss, ng) + 3*ss;
                len = Math.max(len, cur);
            }
        }
        System.out.println(len);
    }

    static long pow(long a, long b){
        long res = 1;
        while(b > 0){
            if(b%2 == 1){
                res = (res * a%mod)%mod;
            }
            a = (a%mod*a%mod)%mod;
            b >>= 1;
        }
        return res%mod;
    }

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static class Pair<T, U>  {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Pair<?,?>)) return false;
            Pair<T, U> pair = (Pair<T, U>) obj;
            return f.equals(pair.f) && s.equals(pair.s);
        }
    }

    static int mod = (int)1e9 + 7;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

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
            return java.lang.Integer.parseInt(next());
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
}

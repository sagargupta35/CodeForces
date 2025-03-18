 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        FastWriter out = new FastWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[] a = ai(n);
        int[] b = ai(m);
        long as = 0, bs = 0;
        for (int i = 0; i < n; i++) {
            as += a[i];
        }
        for (int i = 0; i < m; i++) {
            bs += b[i];
        }
        NavigableSet<Long> sa = new TreeSet<>();
        NavigableSet<Long> sb = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            sa.add(as-a[i]);
        }
        for (int i = 0; i < m; i++) {
            sb.add(bs-b[i]);
        }
        boolean [] pos = new boolean[(int) (2*(1e5) + 5)];
        boolean [] npos = new boolean[pos.length];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = update(i, sa, sb);
            npos[i] = update(-i, sa, sb);
        }
        while (q-- > 0){
            int x = in.nextInt();
            boolean flag;
            if(x >= 0){
                flag = pos[x];
            } else{
                flag = npos[-x];
            }
            out.println(flag ? "YES" : "NO");
        }
        out.close();
    }

    static boolean update(int x, NavigableSet<Long> sa, NavigableSet<Long> sb){
        int max = (int)Math.sqrt(Math.abs(x));
        boolean flag = false;
        for (long i = 1; i <= max; i++) {
            if(x > 0){
                if((x)%i == 0){
                    if((sa.contains(i) && sb.contains(x/i)) || (sa.contains(x/i) && sb.contains(i))){
                        flag = true;
                        break;
                    }
                    if((sa.contains(-i) && sb.contains(-x/i)) || (sa.contains(-x/i) && sb.contains(-i))){
                        flag = true;
                        break;
                    }
                }
            } else{
                if((-x)%i == 0){
                    if((sa.contains(i) && sb.contains(x/i)) || (sa.contains(x/i) && sb.contains(i))){
                        flag = true;
                        break;
                    }
                    if((sa.contains(-i) && sb.contains(-x/i)) || (sa.contains(-x/i) && sb.contains(-i))){
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
    
    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(f, pair.f) && Objects.equals(s, pair.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(f, s);
        }

        @Override
        public String toString() {
            return f + " " + s;
        }
    }

    static int mod = 998244353;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }


    static void reverse(int [] arr, int i, int j){
        while (i <= j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
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
}

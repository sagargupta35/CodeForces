import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] arr = ai(n - 1);
            int[] p = ai(n);
            int k = (int)(Math.log(n+1)/Math.log(2));
            int cnt = n-1;
            boolean [][] good = new boolean[n][2];
            int [] cp = new int [n];
            for (int i = 0; i < n; i++) {
                cp[i] = p[i];
                cnt = update(good, cp, i, k, cnt);
            }
            while (q-- > 0){
                int x = in.nextInt()-1;
                int y = in.nextInt()-1;
                int temp = cp[x];
                cp[x] = cp[y];
                cnt = update(good, cp, x, k, cnt);
                cp[y] = temp;
                cnt = update(good, cp, y, k, cnt);
                if(cnt == 0){
                    System.out.println("YES");
                } else System.out.println("NO");
            }
        }
    }

    static int update(boolean [][] good, int [] p, int idx, int k, int cnt){
        int par = p[idx]/2;
        int level = (int)(Math.log(p[idx])/Math.log(2));
        int node = p[idx];
        int ord = node&1;
        int down = k-level-1;
        int size = (1 << (down+1)) - 1;
        int n = p.length;

        // check for par
        if(node != 1){
            if(idx > 0){
                if(p[idx-1] == par){
                    if(!good[par][ord]){
                        good[par][ord] = true;
                        cnt--;
                    }
                } else{
                    if(idx-size-1 >= 0){
                        if(p[idx-size-1] == par){
                            if(!good[par][ord]){
                                good[par][ord] = true;
                                cnt--;
                            }
                        } else{
                            if(good[par][ord]){
                                good[par][ord] = false;
                                cnt++;
                            }
                        }
                    } else{
                        if(good[par][ord]){
                            good[par][ord] = false;
                            cnt++;
                        }
                    }
                }
            } else{
                if(good[par][ord]){
                    good[par][ord] = false;
                    cnt++;
                }
            }
        }

        // check for child
        if(level != k-1){
            if(idx == n-1){
                for (int i = 0; i < 2; i++) {
                    if(good[node][i]){
                        good[node][i] = false;
                        cnt++;
                    }
                }
            } else{
                int c1 = 2*node;
                int c2 = 2*node+1;
                int next = (size-1)/2 + 1;
                if(p[idx+1] == c1){
                    if(!good[node][0]){
                        good[node][0] = true;
                        cnt--;
                    }
                } else if(idx+next < n && p[idx+next] == c1){
                    if(!good[node][0]){
                        good[node][0] = true;
                        cnt--;
                    }
                } else{
                    if(good[node][0]){
                        good[node][0] = false;
                        cnt++;
                    }
                }
                // check for c2
                if(p[idx+1] == c2){
                    if(!good[node][1]){
                        good[node][1] = true;
                        cnt--;
                    }
                } else if(idx+next < n && p[idx+next] == c2){
                    if(!good[node][1]){
                        good[node][1] = true;
                        cnt--;
                    }
                } else{
                    if(good[node][1]){
                        good[node][1] = false;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    static class Triplet{
        int a, b, c;

        public Triplet(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c;
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
}

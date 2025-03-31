import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            NavigableSet<Integer> [] adj = new TreeSet [n+1];
            Arrays.setAll(adj, i -> new TreeSet<>());
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            int max = 0;
            int u = -1, v = -1;
            List<Triplet> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                while (adj[i].size() > 1){
                    int b = adj[i].pollFirst();
                    int c = adj[i].pollFirst();
                    adj[b].remove(i);
                    adj[c].remove(i);
                    list.add(new Triplet(i, b, c));
                    if(adj[b].contains(c)){
                        adj[b].remove(c);
                        adj[c].remove(b);
                    } else{
                        adj[b].add(c);
                        adj[c].add(b);
                    }
                }
            }
            for(int i = 1; i<= n; i++){
                if(adj[i].size() == 1){
                    max = 1;
                    u = i;
                    v = adj[i].first();
                    break;
                }
            }
            if(max != 0){
                boolean [] vis = new boolean[n+1];
                vis[u] = true;
                vis[v] = true;
                for (int i = 1; i <= n; i++) {
                    if(vis[i]) continue;
                    vis[i] = true;
                    if(adj[i].isEmpty()){
                        list.add(new Triplet(u, v, i));
                        v = i;
                    } else{
                        int j = adj[i].pollFirst();
                        adj[j].pollFirst();
                        list.add(new Triplet(u, i, j));
                        vis[j] = true;
                    }
                }
            }
            System.out.println(list.size());
            for(var tt : list){
                System.out.println(tt);
            }
        }
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

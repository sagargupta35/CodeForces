import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int [] arr = ai(n);
            Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
            for(int j : arr){
                int cur = j - (j%4);
                map.putIfAbsent(cur, new PriorityQueue<>());
                map.get(cur).add(j);
            }
            for (int i = 0; i < n; i++) {
                int cur = arr[i] - arr[i]%4;
                arr[i] = map.get(cur).poll();
            }
            for(var j : arr){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    static class Dsu{

        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        public Dsu(int n) {
            for (int i = 0; i <= n; i++) {
                size.add(1);
                parent.add(i);
            }
        }

        int findPar(int u){
            if(parent.get(u) == u) return u;
            int par = findPar(parent.get(u));
            parent.set(u, par);
            return par;
        }

        void union(int u, int v){
            int up = findPar(u);
            int vp = findPar(v);
            if(up == vp) return;
            if(size.get(up) < size.get(vp)){
                parent.set(up, vp);
                size.set(vp, size.get(up) + size.get(vp));
            } else{
                parent.set(vp, up);
                size.set(up, size.get(up) + size.get(vp));
            }
        }

    }

    static int [] zFun(String str) {
        int n = str.length();
        int l = 0, r = 0;
        int [] z = new int [str.length()];
        for(int i = 1; i < n; ++i) {
            if(i > r){
                l = r = i;
                while(r < n && str.charAt(r - l) == str.charAt(r)) r++;
                z[i] = r - l;
                r--;
            }
            else{
                int k = i - l;
                if(z[k] < r - i + 1) z[i] = z[k];
                else{
                    l = i;
                    while(r < n && str.charAt(r - l) == str.charAt(r))
                        r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
        return z;
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec){
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }
    }

    static int mod = (int)1e9 + 7;

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

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

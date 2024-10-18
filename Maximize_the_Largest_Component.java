import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            String [] arr = new String[n];
            Arrays.setAll(arr, i -> in.nextLine());
            Dsu d = getDsu(n, m, arr);

            // make rows #
            int [] rows = {0, 0, 1, -1};
            int [] cols = {1, -1, 0, 0};
            int ans = 0;
            for (int i = 0; i < n; i++) {
                // make ith row #
                Set<Integer> parents = new HashSet<>();
                for (int j = 0; j < m; j++) {
                    parents.add(d.findPar(i*m + j));
                    for (int k = 0; k < 4; k++) {
                        int di = rows[k]+i;
                        int dj = cols[k]+j;
                        if(di >= 0 && di < n && dj >= 0 && dj < m){
                            if(arr[di].charAt(dj) == '#'){
                                parents.add(d.findPar(di*m + dj));
                            }
                        }
                    }
                }
                int cur = 0;
                for(int j : parents){
                    cur += d.size.get(j);
                }
                ans = Math.max(ans, cur);
            }
            
            // make cols #
            for (int j = 0; j < m; j++) {
                // make jth column #
                Set<Integer> parents = new HashSet<>();
                for (int i = 0; i < n; i++) {
                    parents.add(d.findPar(i*m + j));
                    for (int k = 0; k < 4; k++) {
                        int di = rows[k]+i;
                        int dj = cols[k]+j;
                        if(di >= 0 && di < n && dj >= 0 && dj < m){
                            if(arr[di].charAt(dj) == '#'){
                                parents.add(d.findPar(di*m + dj));
                            }
                        }
                    }
                }
                int cur = 0;
                for(int parent : parents){
                    cur += d.size.get(parent);
                }
                ans = Math.max(ans, cur);
            }

            System.out.println(ans);
        }
    }

    private static Dsu getDsu(int n, int m, String[] arr) {
        Dsu d = new Dsu(n * m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i].charAt(j) == '#'){
                    if(i < n-1){
                        if(arr[i+1].charAt(j) == '#'){
                            d.union(i* m + j, i* m + m +j);
                        }
                    }
                    if(j < m-1){
                        if(arr[i].charAt(j+1)== '#'){
                            d.union(i* m + j, i* m + j + 1);
                        }
                    }
                }
            }
        }
        return d;
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

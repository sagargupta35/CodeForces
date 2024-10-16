import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        if(a != 1 && b != 1){
            System.out.println("NO");
            return;
        }
        if(n == 1){
            System.out.println("YES");
            System.out.println(0);
            return;
        }
        if(n == 2){
            if(a == 1 && b == 1){
                System.out.println("NO");
                return;
            }
            System.out.println("YES");
            if(a == 1){
                System.out.println("01");
                System.out.println("10");
            } else{
                System.out.println("00");
                System.out.println("00");
            }
            return;
        }
        if(n == 3){
            if(a == 1 && b == 1){
                System.out.println("NO");
                return;
            }
        }
        int [][] adj = new int [n][n];
        System.out.println("YES");
        if(a == 1 && b == 1){
            for (int i = 0; i < n - 1; i++) {
                adj[i][i+1] = 1;
                adj[i+1][i] = 1;
            }
            for(int [] arr : adj){
                for(var j : arr){
                    System.out.print(j);
                }
                System.out.println();
            }
            return;
        }
        Dsu d = new Dsu(n);
        int c = a == 1 ? b : a;
        for (int i = 0; i < c; i++) {
            for (int j = i; j < n; j+=c) {
                d.union(j, i);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(a == 1){
                    if(d.findPar(i) != d.findPar(j)){
                        adj[i][j] = 1;
                        adj[j][i] = 1;
                    }
                } else{
                    if(d.findPar(i) == d.findPar(j)){
                        adj[i][j] = 1;
                        adj[j][i] = 1;
                    }
                }
            }
        }
        for(var arr : adj){
            for(int j : arr){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static class Dsu{

        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        public Dsu(int n) {
            for (int i = 0; i < n; i++) {
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

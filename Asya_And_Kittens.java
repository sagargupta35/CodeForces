import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        Dsu dsu = new Dsu(n);
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            dsu.union(u, v);
        }
        int parent = dsu.findPar(1);
        while (parent != -1){
            System.out.print(parent + " ");
            parent = dsu.next.get(parent);
        }
        System.out.println();
    }

    static class Dsu{

        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        List<Integer> next = new ArrayList<>();

        public Dsu(int n) {
            for (int i = 0; i <= n; i++) {
                size.add(1);
                parent.add(i);
                start.add(i);
                end.add(i);
                next.add(-1);
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
            if(size.get(up) < size.get(vp)){ // parent of up = vp
                parent.set(up, vp);
                size.set(vp, size.get(up) + size.get(vp));
                next.set(end.get(vp), start.get(up));
                end.set(vp, end.get(up));
            } else{ // parent of vp = up
                parent.set(vp, up);
                size.set(up, size.get(up) + size.get(vp));
                next.set(end.get(up), start.get(vp));
                end.set(up, end.get(vp));
            }
        }

    }

    static int aMax(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int j : arr) max = Math.max(max,j);
        return max;
    }

    static int aMin(int [] arr){
        int min = Integer.MAX_VALUE;
        for(int j : arr) min = Math.min(min, j);
        return min;
    }

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

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

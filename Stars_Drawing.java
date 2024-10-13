import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        String [] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLine();
        }
        boolean [][] vis = new boolean[n][m];
        List<Pair<Integer, Pair<Integer, Integer>>> list = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if(arr[i].charAt(j) == '*'){
                    int u = i-1, d = i+1, l = j-1, r = j+1;
                    int cnt = 0;
                    while (u >= 0 && d < n && l >= 0 && r < m){
                        if(arr[i].charAt(l) != '*' || arr[i].charAt(r) != '*' || arr[u].charAt(j) != '*' || arr[d].charAt(j) != '*') break;
                        vis[i][l] = vis[i][r] = vis[u][j] = vis[d][j] = true;
                        cnt++;
                        u--; d++; l--; r++;
                    }
                    if(cnt > 0){
                        vis[i][j] = true;
                        list.add(new Pair<>(cnt, new Pair<>(i, j)));
                    }
                }
            }
        }
        boolean flag = true;
        for (int i = 0; (i < n) & flag; i++) {
            for (int j = 0; (j < m) & flag; j++) {
                if(arr[i].charAt(j) == '*'){
                    flag = vis[i][j];
                }
            }
        }
        if(!flag){
            System.out.println(-1);
        } else {
            System.out.println(list.size());
            for(var p : list){
                System.out.println((p.s.f+1) + " " + (p.s.s+1) + " " + p.f);
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

    static int mod = (int) 1e9 + 7;

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

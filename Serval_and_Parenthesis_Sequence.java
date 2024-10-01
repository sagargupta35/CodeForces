import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        StringBuilder sbr = new StringBuilder(in.nextLine());
        if((n&1) == 1){
            System.out.println(":(");
            return;
        }
        if(sbr.charAt(0) == '?') sbr.setCharAt(0, '(');
        if(sbr.charAt(n-1) == '?') sbr.setCharAt(n-1, ')');
        if(sbr.charAt(0) == ')' || sbr.charAt(n-1) == '('){
            System.out.println(":(");
            return;
        }
        int cnt = 1;
        int left = n/2;
        for (int i = 0; i < n; i++) {
            if(sbr.charAt(i) == '(') left--;
        }
        if(left < 0){
            System.out.println(":(");
            return;
        }
        for (int i = 1; i < n - 1; i++) {
            if(sbr.charAt(i) == '('){
                cnt++;
            } else if(sbr.charAt(i) == ')'){
                cnt--;
            } else{
                if(left > 0){
                    sbr.setCharAt(i, '(');
                    left--;
                    cnt++;
                } else{
                    cnt--;
                    sbr.setCharAt(i, ')');
                }
            }
            if(cnt == 0) break;
        }
        if(cnt == 0){
            System.out.println(":(");
            return;
        }
        System.out.println(sbr);
    }

    static boolean delete(List<Integer> [] adj, int [] res, int root, List<Integer> del){
        boolean should = res[root] == 1;
        for(int j : adj[root]){
            should = should & delete(adj, res, j, del);
        }
        if(should) del.add(root);
        return res[root] == 1;
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

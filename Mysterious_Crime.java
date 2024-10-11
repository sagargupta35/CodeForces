import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        int [][] arr = new int [m][n];
        int [] first = ai(n);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][in.nextInt()-1] = j;
            }
        }
        for (int i = 0; i < n; i++) {
            first[i]--;
            arr[0][first[i]] = i;
        }
        long sum = 0;
        int [] next = new int [m];
        for (int i = 0; i < m; i++) {
            next[i] = arr[i][first[0]]-1;
        }
        long cur = 0;
        for (int i = 0; i < n; i++) {
            if(check(arr, next, first[i])){
                cur++;
            } else{
                sum += (cur*(cur+1))/2;
                cur = 1;
                for (int j = 0; j < m; j++) {
                    next[j] = arr[j][first[i]];
                }
            }
        }
        sum += (cur * (cur+1))/2;
        System.out.println(sum);
    }

    static boolean check(int [][] arr, int [] next, int target){
        for (int i = 0; i < next.length; i++) {
            if(next[i] == arr[i][target]-1) next[i]++;
            else return false;
        }
        return true;
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

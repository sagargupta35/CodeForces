import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        String [][] arr = new String[n-1][2];
        String [] ord = new String[2*n-2];
        for (int i = 0; i < 2 * n - 2; i++) {
            String s = in.nextLine();
            if(arr[s.length()-1][0] == null) arr[s.length()-1][0] = s;
            else arr[s.length()-1][1] = s;
            ord[i] = s;
        }
        boolean [][] flags = new boolean[n-1][2];
        sol(arr, flags, "");
        for(String s : ord){
            int l = s.length()-1;
            if(arr[l][0].equals(s)){
                if(flags[l][0]){
                    System.out.print("P");
                    flags[l][0] = false;
                } else{
                    flags[l][0] = true;
                    System.out.print("S");
                }
            } else{
                if(flags[l][1]){
                    flags[l][1] = false;
                    System.out.print("P");
                } else{
                    flags[l][1] = true;
                    System.out.print("S");
                }
            }
        }
        System.out.println();
    }

    static boolean sol(String [][] arr, boolean [][] flags, String prev){
        if(prev.length() == arr.length){
            return check(arr, flags);
        }
        if(arr[prev.length()][0].startsWith(prev)){
            flags[prev.length()][0] = true;
            if(sol(arr, flags, arr[prev.length()][0])){
                return true;
            }
            flags[prev.length()][0] = false;
        }
        if(arr[prev.length()][1].startsWith(prev)){
            flags[prev.length()][1] = true;
            if(sol(arr, flags, arr[prev.length()][1])){
                return true;
            }
            flags[prev.length()][1] = false;
        }
        return false;
    }

    static boolean check(String [][] arr, boolean [][] flags){
        String prev = flags[0][0] ? arr[0][1] : arr[0][0];
        for (int i = 1; i < arr.length; i++) {
            if(flags[i][0]){
                if(!arr[i][1].endsWith(prev)) return false;
                prev = arr[i][1];
            } else{
                if(!arr[i][0].endsWith(prev)) return false;
                prev = arr[i][0];
            }
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

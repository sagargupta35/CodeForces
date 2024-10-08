import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        int [][] arr = new int [m][2];
        for (int i = 0; i < m; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }
        if(check(arr, arr[0][0]) || check(arr, arr[0][1])) System.out.println("YES");
        else System.out.println("NO");

    }

    static boolean check(int [][] arr, int doubt){
        int m = arr.length;
        boolean [] flags = new boolean [m];
        for (int i = 0; i < m; i++) {
            if(arr[i][0] == doubt || arr[i][1] == doubt) flags[i] = true;
        }
        return check(arr, flags);
    }

    static boolean check(int [][] arr, boolean [] flags){
        int cnt1 = 0, cnt2 = 0, idx = -1, tot = 0;
        int m = arr.length;
        for (int i = 0; i < m; i++) {
            if(!flags[i]){
                if(idx == -1){
                    idx = i;
                    cnt1++;
                    cnt2++;
                } else{
                    if(arr[idx][0] == arr[i][0] || arr[idx][0] == arr[i][1]) cnt1++;
                    if(arr[idx][1] == arr[i][0] || arr[idx][1] == arr[i][1]) cnt2++;
                }
                tot++;
            }
        }
        return tot == cnt1 || tot == cnt2;
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

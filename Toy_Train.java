import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        int [][] time = new int [n][2];
        for (int i = 0; i < n; i++) {
            time[i][1] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            time[a][0]++;
            int cur = b - a;
            if(cur < 0) cur += n;
            time[a][1] = Math.min(time[a][1], cur);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(totalTime(time, i) + " ");
        }
        System.out.println();
    }

    static int totalTime(int [][] time, int st){
        int max = 0;
        int n = time.length;
        for (int i = 0; i < n; i++) {
            int next = (st + i)%n;
            int cur = 0;
            if(time[next][0] > 0){
                cur = (time[next][0]-1)*n + time[next][1];
                int reach = next - st;
                if(reach < 0) reach += n;
                cur += reach;
            }
            max = Math.max(max, cur);
        }
        return max;
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

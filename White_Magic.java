 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            int nz = 0;
            int idx = -1;
            for (int i = 0; i < n; i++) {
                if(arr[i] != 0) nz++;
                else{
                    if(idx == -1) idx = i;
                }
            }
            if(idx == -1){
                System.out.println(nz);
                continue;
            }
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < idx; i++) {
                if(arr[i] != 0){
                    max = Math.min(max, arr[i]);
                }
            }
            Set<Integer> set = new HashSet<>();
            for (int i = n - 1; i >= idx; i--) {
                set.add(arr[i]);
            }
            nz++;
            int [] min = new int [n];
            Arrays.fill(min, Integer.MAX_VALUE);
            for (int i = 0; i < n; i++) {
                if(arr[i] != 0){
                    min[i] = arr[i];
                }
                if(i > 0) min[i] = Math.min(min[i], min[i-1]);
            }
            int mex = 0;
            for (int i = idx - 1; i >= 0; i--) {
                if(arr[i] != 0){
                    while (set.contains(mex)) mex++;
                    if(min[i] < mex){
                        nz--;
                        break;
                    }
                    set.add(arr[i]);
                }
            }
            System.out.println(nz);
        }
    }

    static boolean is(long [] arr, long sum){
        for (long l : arr) {
            if (l == sum) return true;
        }
        return false;
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

    static int mod = 998244353;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }


    static void reverse(int [] arr, int i, int j){
        while (i <= j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
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

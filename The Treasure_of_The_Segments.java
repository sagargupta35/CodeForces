 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            int [][] arr = new int [n][2];
            int [] l = new int [n];
            int [] r = new int [n];
            for (int i = 0; i < n; i++) {
                arr[i] = new int [] {in.nextInt(), in.nextInt()};
                l[i] = arr[i][0];
                r[i] = arr[i][1];
            }
            Arrays.sort(l);
            Arrays.sort(r);
            int ans = n-1;
            for (int i = 0; i < n; i++) {
                int ll = lowerBound(r, arr[i][0]);
                int rr = upperBound(l, arr[i][1]);
                ans = Math.min(ans, ll + rr);
            }
            System.out.println(ans);
        }
    }

    static int lowerBound(int [] arr, int target){
        int ans = -1;
        int lo = 0, hi = arr.length-1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid] >= target){
                hi = mid-1;
            } else{
                ans = mid;
                lo = mid+1;
            }
        }
        return ans+1;
    }

    static int upperBound(int [] arr, int target){
        int ans = arr.length;
        int lo = 0, hi = arr.length-1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid] <= target){
                lo = mid+1;
            } else{
                hi = mid-1;
                ans = mid;
            }
        }
        return arr.length - ans;
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

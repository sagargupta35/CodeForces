import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int k = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int [] arr = ai(k);
        Arrays.sort(arr);
        System.out.println(getCost(1, (1<<n), a, b, arr));
    }

    static long getCost(int st, int end, int a, int b, int [] arr){
        int lo = lowerBound(arr, st);
        int hi = upperBound(arr, end);
        int num = Math.max(hi - lo + 1, 0);
        long cost = num == 0 ? a : (long) num * b * (end - st + 1);
        if(st != end && num > 0){
            long left = getCost(st, st + (end-st)/2, a, b, arr);
            long right = getCost(st + (end-st)/2 + 1, end, a, b, arr);
            cost = Math.min(left+ right, cost);
        }
        return cost;
    }

    static int upperBound(int [] arr, int target){
        int n = arr.length, ans = -1, lo = 0, hi = n-1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid] <= target){
                ans = mid;
                lo = mid+1;
            } else hi = mid-1;
        }
        return ans;
    }

    static int lowerBound(int [] arr, int target){
        int n = arr.length, ans = n, lo = 0, hi = n-1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(arr[mid] >= target){
                ans = mid;
                hi = mid-1;
            } else lo = mid+1;
        }
        return ans;
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

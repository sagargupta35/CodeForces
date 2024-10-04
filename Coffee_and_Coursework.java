import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        int [] arr = ai(n);
        Arrays.sort(arr);
        int lo = 1, hi = n;
        int ans = -1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            long sum = isPossible(arr, mid);
            if(sum >= m){
                ans = mid;
                hi = mid-1;
            } else lo = mid+1;
        }
        System.out.println(ans);
    }

    static long isPossible(int [] arr, int mid){
        int n = arr.length;
        long sum = 0;
        for (int i = 0; i < mid; i++) {
            int total = n/mid;
            if(n%mid > i) total++;
            long cur = 0;
            for (int j = i; j < n; j+=mid) {
                if(arr[j] >= total-1){
                    cur += arr[j];
                } else total--;
            }
            if(total > 0) cur -= sumOfNumbers(0, total-1);
            sum += cur;
        }
        return sum;
    }

    static long sumOfNumbers(long start, long end){
        if(end < start) return 0;
        else if(end == start) return end;
        return (((end*(end+1))/2) - ((start*(start-1))/2));
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

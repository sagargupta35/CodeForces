import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            long[] arr = new long [n+1];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            arr[n] = arr[0];
            long[] d = al(m);
            long[] f = al(k);
            Arrays.sort(d);
            Arrays.sort(f);
            long dMax = Integer.MIN_VALUE;
            int idx = -1, cnt = 0;
            for (int i = 0; i < n - 1; i++) {
                long diff = arr[i + 1] - arr[i];
                if(diff > dMax){
                    dMax = diff;
                    idx = i;
                    cnt = 1;
                } else if(dMax == diff){
                    cnt++;
                }
            }
            if(cnt > 1 || dMax == 1){
                System.out.println(dMax);
                continue;
            }
            long lo = 1, hi = dMax/2;
            while (lo <= hi){
                long diff = lo + (hi - lo)/2;
                long cur = check(d, f, Math.min(arr[idx] + diff, arr[idx+1] - diff), Math.max(arr[idx] + diff, arr[idx+1] - diff));
                if(cur != -1){
                    arr[n] = cur;
                    lo = diff+1;
                } else{
                    hi = diff-1;
                }
            }
            Arrays.sort(arr);
            dMax = -1;
            for (int i = 0; i < n; i++) {
                dMax = Math.max(dMax, arr[i+1] - arr[i]);
            }
            System.out.println(dMax);
        }
    }

    static long check(long [] d, long [] f, long l1, long l2){
        for (int i = 0; i < d.length && d[i] < l2; i++) {
            long lb = lowerBound(f, l2 - d[i]);
            long  sum = d[i] + lb;
            if(sum >=  l1 && sum <= l2) return sum;
        }
        return -1;
    }

    static long lowerBound(long [] f, long target){
        int lo = 0, hi = f.length-1;
        long ans = Integer.MAX_VALUE;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(f[mid] <= target){
                ans = f[mid];
                lo = mid+1;
            } else hi = mid-1;
        }
        return ans;
    }

    static long pow(long a, long b){
        long res = 1;
        while(b > 0){
            if(b%2 == 1){
                res = (res * a%mod)%mod;
            }
            a = (a%mod*a%mod)%mod;
            b >>= 1;
        }
        return res%mod;
    }

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static class Pair<T, U>  {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Pair<?,?>)) return false;
            Pair<T, U> pair = (Pair<T, U>) obj;
            return f.equals(pair.f) && s.equals(pair.s);
        }
    }

    static int mod = (int)1e9 + 7;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
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
            return java.lang.Integer.parseInt(next());
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

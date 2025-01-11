import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            long[] arr = al(n);
            int [] r1 = getMinTime(arr);
            reverse(arr);
            int [] r2 = getMinTime(arr);
            for (int i = 0; i < n; i++) {
                int cur = Math.min(r1[i], r2[n-i-1]);
                if(cur != Integer.MAX_VALUE){
                    System.out.print(cur + " ");
                } else System.out.print("-1 ");
            }
            System.out.println();
        }
    }

    static void reverse(long [] arr){
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            long temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }

    }

    static int [] getMinTime(long [] arr){
        int n = arr.length;
        int [] res = new int [n];
        Arrays.fill(res, Integer.MAX_VALUE);
        long [] suf = new long [n];
        suf[n-1] = arr[n-1];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] += suf[i+1] + arr[i];
        }
        int pre = 0;
        long pr = arr[0];
        for (int i = 1; i < n; i++) {
            if(arr[i-1] != pr){
                pre = i-1;
                pr = arr[i-1];
            }
            if(arr[i-1] > arr[i]){
                res[i] = 1;
                continue;
            }
            int j = binarySearch(suf, pre-1, arr[i], suf[i]);
            if(j != -1){
                res[i] = i - j;
            }
        }
        return res;
    }

    static int binarySearch(long [] suf, int idx, long target, long diff){
        int lo = 0, hi = idx, ans = -1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(suf[mid] - diff > target){
                ans = mid;
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

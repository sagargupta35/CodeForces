
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int c = in.nextInt();
            int [] vis = new int[c+10];
            int[] arr = ai(n);
            NavigableSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                vis[arr[i]]++;
                set.add(arr[i]);
            }
            for (int i = 1; i < vis.length; i++) {
                vis[i] += vis[i-1];
            }
            if(vis[1] == 0){
                System.out.println("No");
                continue;
            }
            boolean flag = true;
            for(int j : set){
                if(j == 1){
                    continue;
                }
                int max = c/j;
                for (int i = 2; i <= max; i++) {
                    if(vis[i] - vis[i-1] == 0){
                        int lo = j*i - 1;
                        int hi = Math.min(c, j*(i+1)-1);
                        if(lo == hi){
                            if(vis[hi] - vis[hi-1] > 0){
                                flag = false;
                                break;
                            }
                            continue;
                        }
                        if(vis[hi] - vis[lo] > 0){
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(flag){
                System.out.println("Yes");
            } else System.out.println("No");
        }
    }

    static void reverse(int [] arr){
        int n = arr.length;
        for (int i = 0; i < n/2; i++) {
            int temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }
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

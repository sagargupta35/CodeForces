import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int q =in.nextInt();
        int[] arr = ai(n);
        long sum = ((long)n * (n+1))/2;
        for (int i = 1; i < n; i++) {
            if(arr[i] != arr[i-1]){
                sum += (long) (n - i) *i;
            }
        }
        while (q-- > 0){
            int i = in.nextInt()-1;
            int x = in.nextInt();
            if(i > 0){
                if(arr[i-1] != arr[i]){
                    sum -= (long) (n - i) *i;
                }
                if(arr[i-1] != x){
                    sum += (long) (n - i) *i;
                }
            }
            if(i < n-1){
                if(arr[i] != arr[i+1]){
                    sum -= (long)(n - (i+1)) * (i+1);
                }
                if(arr[i+1] != x){
                    sum += (long)(n - (i+1)) * (i+1);
                }
            }
            arr[i] = x;
            System.out.println(sum);
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

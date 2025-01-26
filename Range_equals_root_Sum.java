import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            if((n&1) == 0){
                for (int i = n / 2; i <= n +(n/2); i++) {
                    if(i != n){
                        System.out.print(i + " ");
                    }
                }
                System.out.println();
            } else{
                int [] arr = new int [n];
                for (int i = n - (n / 2), next = 0; i <= n+(n/2); i++, next++) {
                    arr[next] = i + 2;
                }
                arr[0]--;
                arr[n-1]++;
                arr[n-2]++;
                for(int j : arr){
                    System.out.print(j + " ");
                }
                System.out.println();
            }
        }
    }

    static void add(int [] arr, int p, Set<Integer> set){
        int mod = 1;
        int n = arr.length;
        arr[n-1] = p-1;
        for (int i = n - 1; i >= 0; i--) {
            int cur = arr[i] + mod;
            if(cur >= p){
                arr[i] = 0;
            } else{
                arr[i] = cur;
                mod = 0;
            }
            set.add(arr[i]);
        }
        if(mod == 1) set.add(mod);
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

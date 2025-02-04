
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        long [][] arr = new long [3][];
        Arrays.setAll(arr, i -> al(2));
        List<long []> res = new ArrayList<>();
        long [] x = new long [] {arr[0][0], arr[1][0], arr[2][0]};
        long [] y = new long [] {arr[0][1], arr[1][1], arr[2][1]};
        Arrays.sort(x);
        Arrays.sort(y);
        long xm = x[1], ym = y[1];
        for(var point : arr){
            if(xm != point[0] || ym != point[1]){
                Collections.addAll(res, getEquations(point[0], point[1], xm, ym));
            }
        }
        System.out.println(res.size());
        for(var line : res){
            for(long j : line) System.out.print(j + " ");
            System.out.println();
        }
    }

    static long dist(long x1, long y1, long x2, long y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static long [][] getEquations(long x1, long y1, long x2, long y2){
        if(x1 == x2 || y1 == y2){
            return new long [][] {{x1, y1, x2, y2}};
        }
        return new long [][] {{x1, y1, x1, y2}, {x1, y2, x2, y2}};
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

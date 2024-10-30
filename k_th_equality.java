import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    static class Condition{
        int a;
        int b;
        int d;
        int idx;

        public Condition(int a, int b, int d, int idx) {
            this.a = a;
            this.b = b;
            this.d = d;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            long k = in.nextLong();
            if(c < Math.max(a, b)){
                System.out.println(-1);
                continue;
            } else if(c > Math.max(a, b) + 1){
                System.out.println(-1);
                continue;
            }
            int[] ab = bounds(a);
            int aa = -1, bb = -1;
            for (int i = ab[0]; i <= ab[1]; i++) {
                var p = binarySearch(i, b, c, k);
                if (p.s) {
                    aa = i;
                    bb = p.f;
                    break;
                } else k -= p.f;
            }
            if (aa != -1) {
                System.out.println(aa + " + " + bb + " = " + (aa + bb));
                continue;
            }
            System.out.println(-1);
        }
    }

    static Pair<Integer, Boolean> binarySearch(int a, int b, int c, long k){
        var bb = bounds(b);
        var cb = bounds(c);
        int da = digits(a);
        int min, max;
        if(c == Math.max(da, b)){
            if(digits(bb[0] + a) > c) return new Pair<>(0, false);
            min = bb[0];
            max = Math.min(bb[1], cb[1]-a);
        } else{
            if(digits(a + bb[1]) < c) return new Pair<>(0, false);
            min = Math.max(bb[0], cb[0]-a);
            max = bb[1];
        }
        if(max - min + 1 >= k){
            return new Pair<>(min + (int) k - 1, true);
        }
        return new Pair<>(max - min + 1, false);
    }

    static int [] bounds(int j){
        if(j == 1) return new int [] {1, 9};
        int min = (int)Math.pow(10, j-1);
        int max = (int)Math.pow(10, j)-1;
        return new int [] {min, max};
    }

    static int digits(long a){
        long cur = 1;
        int ans = 0;
        while (cur <= a){
            cur *= 10;
            ans++;
        }
        return ans;
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

    static class  Triplet<T>{
        T x, y, z;

        public Triplet(T x, T y, T z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static Triplet<Long> extendedEuclid(long a, long b){ // a>b
        if(b == 0){
            return new Triplet<>(a, 0L, a);
        }

        Triplet<Long> smallAns = extendedEuclid(b, a%b);
        long y = smallAns.x - (a/b)*smallAns.y;
        return new Triplet<>(smallAns.y, y, smallAns.z);

    }

    static long modularMultiplicativeInverse(long a, long m){
        long gcd = gcd(a, m);
        if(gcd != 1){
            return -1;
        }
        long x = extendedEuclid(a, m).x;
        x = (x%m + m)%m;
        return x;
    }

    static long nCrModPFermat(int n, int r, int p, long [] fac) {
        if (n<r) return 0;
        if (r == 0) return 1;

        return ((fac[n] * modularMultiplicativeInverse(fac[r], p)) % p *
                (modularMultiplicativeInverse(fac[n - r], p)) % p) % p;
    }

    static class Pair<T, U> {
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

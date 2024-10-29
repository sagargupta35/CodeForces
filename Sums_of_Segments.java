import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int[] arr = ai(n);
        int q = in.nextInt();
        long [] p1 = new long [n];
        p1[0] = arr[0];
        for (int i = 1; i < n; i++) {
            p1[i] += p1[i-1] + arr[i];
        }
        long [] p2 = new long [n];
        p2[0] = p1[0];
        for (int i = 1; i < n; i++) {
            p2[i] += p2[i-1] + p1[i];
        }
        Function<Integer, Long> startIndex = i -> sumOfNumbers(n-i+2, n) + 1;
        long [] mid = new long [n];
        mid[0] = f1(p1, p2, 0, 0, n-1);
        for (int i = 1; i < n; i++) {
            mid[i] += mid[i-1] + f1(p1, p2, i, i, n-1);
        }

        while (q-- > 0){
            long i1 = in.nextLong();
            long i2 = in.nextLong();
            int l1 = binarySearch(n, i1);
            int l2 = binarySearch(n, i2);
            int r1 = (int) (i1 - startIndex.apply(l1) + l1)-1;
            int r2 = (int) (i2 - startIndex.apply(l2) + l2)-1;
            l1--; l2--;
            if(l1 == l2){
                System.out.println(f1(p1, p2, l1, r1, r2));
            } else{
                long sum = f1(p1, p2, l2, l2, r2) + f1(p1, p2, l1, r1, n-1);
                if(l2 - l1 > 1){
                    sum += mid[l2-1];
                    sum -= mid[l1];
                }
                System.out.println(sum);
            }
        }
    }

    static long f1(long [] p1, long [] p2, int l, int r1, int r2){
        long sum = p2[r2];
        if(r1 > 0) sum -= p2[r1-1];
        if(l > 0){
            sum -= (p1[l-1]*(r2 - r1 + 1));
        }
        return sum;
    }

    static int binarySearch(int n, long target){
        int lo = 1, hi = n, ans = -1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            long cur = sumOfNumbers(n-mid+1, n);
            if(target <= cur){
                ans = mid;
                hi = mid-1;
            } else lo = mid+1;
        }
        return ans;
    }

    static long sumOfNumbers(long start, long end){
        if(end < start) return 0;
        else if(end == start) return end;
        return (((end*(end+1))/2) - ((start*(start-1))/2));
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

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            Boolean z = checkZero(arr);
            if(z != null){
                if(z) System.out.println(0);
                else System.out.println(-1);
                continue;
            }
            Integer pos = null;
            Integer neg = null;
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                int j = arr[i] - arr[i-1];
                if(j > 0){
                    if(pos == null) pos = j;
                    else if(pos != j){
                        flag = false;
                        break;
                    }
                } else if(j < 0){
                    if(neg == null){
                        neg = j;
                    } else if(neg != j){
                        flag = false;
                        break;
                    }
                }
            }
            if(!flag){
                System.out.println(-1);
                continue;
            }
            if(pos == null || neg == null){
                System.out.println(0);
                continue;
            }
            int m = pos + (neg * -1);
            if(check(arr, m, pos)){
                System.out.println(m + " " + pos);
            } else System.out.println(-1);
        }
    }

    static boolean check(int [] arr, int m, int c){
        boolean flag = true;
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = (pre + c)%m;
            if(pre != arr[i]) return false;
        }
        for(int j : arr) if(j >= m) return false;
        return true;
    }

    static Boolean checkZero(int [] arr){
        boolean allSame = true;
        for(int j : arr)
            if(j != arr[0]){
                allSame = false;
                break;
            }
        if(allSame) return true;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] == arr[i-1]) return false;
        }
        return null;
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

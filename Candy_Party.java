import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long[] arr = al(n);
            long sum = 0;
            for (long j : arr) {
                sum += j;
            }
            if (sum % n != 0) {
                System.out.println("No");
                continue;
            }
            Arrays.sort(arr);
            int avg = (int) (sum / n);
            Map<Long, Integer> map = new HashMap<>();
            boolean flag = true;
            for (long j : arr) {
                long diff;
                if (j < avg) {
                    diff = avg - j;
                    if((diff & (diff-1)) == 0){
                        map.merge(diff*2, -1, Integer::sum);
                        map.merge(diff, 1, Integer::sum);
                    } else{
                        long cur = 1;
                        while (cur < diff) cur *= 2;
                        long temp = cur - diff;
                        if((temp & (temp-1)) != 0){
                            flag = false;
                            break;
                        }
                        map.merge(cur, -1, Integer::sum);
                        map.merge(temp, 1, Integer::sum);
                    }
                } else if (j > avg) {
                    diff = j - avg;
                    if((diff & (diff-1)) == 0){
                        if (diff * 2 > j) {
                            flag = false;
                            break;
                        }
                        map.merge(diff*2, 1, Integer::sum);
                        map.merge(diff, -1, Integer::sum);
                    } else{
                        long cur = 1;
                        while (cur < diff) cur *= 2;
                        long temp = cur - diff;
                        if((temp & (temp-1)) != 0){
                            flag = false;
                            break;
                        }
                        map.merge(temp, -1, Integer::sum);
                        map.merge(cur, 1, Integer::sum);
                    }
                }
            }
            for (int j : map.values()) {
                if (j != 0) {
                    flag = false;
                    break;
                }
            }
            if(!flag){
                System.out.println("No");
                continue;
            }
            System.out.println("YES");
        }
    }

    static int log2(long j){
        return (int)(Math.log(j)/Math.log(2));
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

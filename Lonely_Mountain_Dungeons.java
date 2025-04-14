import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long b = in.nextLong();
            long x = in.nextLong();
            long[] arr = al(n);
            long max = 1;
            for(long j : arr) max = Math.max(max, j);
            int lo = 1, hi = (int)max;
            long ans = 0;
            while (lo <= hi){
                int mid = lo + (hi - lo)/2;
                long sum = -(mid-1)*x;
                for(long j :arr){
                    sum += getPairs(j, mid)*b;
                }
                long left = Long.MIN_VALUE;
                long right = Long.MIN_VALUE;
                if(mid > lo){
                    left = -(mid-2)*x;
                    for(long j :arr){
                        left += getPairs(j, mid-1)*b;
                    }
                }
                if(mid < hi){
                    right = -(mid)*x;
                    for(long j : arr){
                        right += getPairs(j, mid+1)*b;
                    }
                }
                ans = Math.max(ans, sum);
                if(left < sum && sum > right){
                    ans = sum;
                    break;
                }
                if(left == sum || right == sum){
                    if(left == sum){
                        if(sum > right){
                            hi = mid-1;
                        } else{
                            ans = right;
                            lo = mid+1;
                        }
                    } else{
                        if(sum > left){
                            lo = mid+1;
                        } else{
                            ans = left;
                            hi = mid-1;
                        }
                    }
                }
                if(left < sum && sum < right){
                    ans = right;
                    lo = mid+1;
                } else if(left > sum && sum > right){
                    ans = left;
                    hi = mid-1;
                }
            }
            System.out.println(ans);
        }
    }

    static long getPairs(long num, int n){
        long q = num/n;
        long n1 = sumOfNumbers(1, n-1);
        int mod = (int)(num%n);
        long nm1 = sumOfNumbers(1, n-mod-1);
        long m1 = sumOfNumbers(1, mod-1);
        if(num < n) return m1;
        if(num == n) return n1;
        if(mod == 0) return q*q*n1;
        return ((q+1)*(q+1)*(n1 - nm1)) - ((q+1)*mod*(n-mod)) + (q*q*nm1);
    }

    static long sumOfNumbers(long start, long end){
        if(end < start) return 0;
        else if(end == start) return end;
        return (((end*(end+1))/2) - ((start*(start-1))/2));
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static long lcm(long u, long v) {
        return (u / gcd(u, v)) * v;
    }

    static class Triplet <T, U, V>{
        T a;
        U b;
        V c;

        public Triplet(T a, U b, V c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) obj;
            return Objects.equals(a, triplet.a) && Objects.equals(b, triplet.b) &&
                    Objects.equals(c, triplet.c);
        }
    }

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(f, pair.f) && Objects.equals(s, pair.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(f, s);
        }

        @Override
        public String toString() {
            return f + " " + s;
        }
    }

    static int mod = 998244353;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }


    static void reverse(int [] arr, int i, int j){
        while (i <= j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
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
            return Integer.parseInt(next());
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

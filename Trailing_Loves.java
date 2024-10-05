import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        long n = in.nextLong();
        long b = in.nextLong();
        var factors = largestPrime(b);
        long zeros = Long.MAX_VALUE;
        for(var entry : factors.entrySet()){
            long key = entry.getKey();
            long temp = n;
            long cur = 0;
            while (temp >= key){
                cur += (temp/key);
                temp /= key;
            }
            zeros = Math.min(zeros, cur / entry.getValue());
        }
        System.out.println(zeros);
    }

    static Map<Long, Long> largestPrime(long b){
        Map<Long, Long> factors = new HashMap<>();
        while (b%2 == 0){
            b /= 2;
            factors.merge(2L, 1L, Long::sum);
        }
        long factor = 3;
        while (factor*factor <= b){
            if(b%factor == 0){
                long sum = 0;
                while (b%factor == 0){
                    b /= factor;
                    sum++;
                }
                factors.put(factor, sum);
            }
            factor += 2;
        }
        if(b > 2){
            factors.merge(b, 1L, Long::sum);
        }
        return factors;
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec){
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

    }

    static int mod = (int) 1e9 + 7;

    static int aMax(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int j : arr) max = Math.max(max,j);
        return max;
    }

    static int aMin(int [] arr){
        int min = Integer.MAX_VALUE;
        for(int j : arr) min = Math.min(min, j);
        return min;
    }

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

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

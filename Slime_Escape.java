import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt()-1;
            long[] arr = al(n);
            Queue<Pair<Long, Pair<Long, Integer>>> qLeft = new LinkedList<>();
            Queue<Pair<Long, Pair<Long, Integer>>> qRight = new LinkedList<>();
            long sum = 0;
            long min = 0;
            for (int i = k - 1; i >= 0; i--) {
                sum += arr[i];
                min = Math.min(min, sum);
                if(sum > 0){
                    qLeft.add(new Pair<>(min, new Pair<>(sum, i)));
                    sum = 0;
                    min = 0;
                }
            }
            sum = 0; min = 0;
            for (int i = k + 1; i < n; i++) {
                sum += arr[i];
                min = Math.min(min, sum);
                if(sum > 0){
                    qRight.add(new Pair<>(min, new Pair<>(sum, i)));
                    sum = 0;
                    min = 0;
                }
            }
            long cur = arr[k];
            int left = k, right = k;
            while (!qLeft.isEmpty() && !qRight.isEmpty()){
                long leftMin = qLeft.peek().f;
                long rightMin = qRight.peek().f;
                if(cur + leftMin < 0 && cur + rightMin < 0){
                    break;
                }
                if(cur + leftMin >= 0){
                    var p = qLeft.poll();
                    cur += p.s.f;
                    left = p.s.s;
                }
                if(cur + rightMin >= 0){
                    var p = qRight.poll();
                    cur += p.s.f;
                    right = p.s.s;
                }
            }
            while (!qLeft.isEmpty() && cur + qLeft.peek().f >= 0){
                var p = qLeft.poll();
                cur += p.s.f;
                left = p.s.s;
            }
            while (!qRight.isEmpty() && cur + qRight.peek().f >= 0){
                var p = qRight.poll();
                cur += p.s.f;
                right = p.s.s;
            }
            if(left == 0 || right == n-1){
                System.out.println("YES");
                continue;
            }
            min = 0; sum = 0;
            for (int i = right + 1; i < n; i++) {
                sum += arr[i];
                min = Math.min(min, sum);
            }
            if(min + cur >= 0){
                System.out.println("YES");
                continue;
            }
            min = 0; sum = 0;
            for (int i = left - 1; i >= 0; i--) {
                sum += arr[i];
                min = Math.min(min, sum);
            }
            if(min + cur >= 0){
                System.out.println("YES");
                continue;
            }
            System.out.println("NO");
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

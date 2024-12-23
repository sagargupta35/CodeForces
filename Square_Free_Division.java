import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int [] sieve = new int [(int)1e7 + 1];
        sieve[1] = 1;
        for (int i = 2; i * i< sieve.length; i++) {
            if(sieve[i] == 0){
                sieve[i] = i;
                for (int j = i * i; j < sieve.length; j += i) {
                    if(sieve[j] == 0) sieve[j] = i;
                }
            }
        }
        int [] mask = new int [sieve.length];
        mask[1] = 1;
        for (int i = 2; i < sieve.length; i++) {
            if(sieve[i] == 0){
                mask[i] = i;
                continue;
            }
            if(mask[i/sieve[i]]%sieve[i] == 0){
                mask[i] = mask[i/sieve[i]] / sieve[i];
            } else mask[i] = mask[i/sieve[i]] * sieve[i];
        }
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = ai(n);
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (set.contains(mask[arr[i]])){
                    sum++;
                    set.clear();
                }
                set.add(mask[arr[i]]);
            }
            if (!set.isEmpty()) sum++;
            System.out.println(sum);
        }
    }

    static int getMask(int [] sieve, int j){
        Map<Integer, Integer> map = new HashMap<>();
        while (j != 1){
            map.merge(sieve[j], 1, Integer::sum);
            j /= sieve[j];
        }
        int mask = 1;
        for(var entry : map.entrySet()){
            if((entry.getValue()&1) == 1) mask *= entry.getKey();
        }
        return mask;
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

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = ai(m);
        int [][] sparse = new int [m][((int)(Math.log(m)/Math.log(2)))+1];
        fill(arr, sparse);
        int q = in.nextInt();
        while (q-- > 0){
            int x1 = in.nextInt()-1;
            int y1 = in.nextInt()-1;
            int x2 = in.nextInt()-1;
            int y2 = in.nextInt()-1;
            int k = in.nextInt();
            if(x1 % k != x2 % k || y1 % k != y2 %k){
                System.out.println("NO");
                continue;
            }
            int j = ((n-1)/k)*k + (x1%k);
            if(j >= n) j-= k;
            if(y1 > y2){
                int temp = y2;
                y2 = y1;
                y1 = temp;
            }
            int max = query(sparse, y1, y2);
            if(max > j){
                System.out.println("NO");
                continue;
            }
            System.out.println("YES");
        }

    }

    static int query(int [][] pre, int i, int j){
        int len = (j - i + 1);
        int log = (int)(Math.log(len)/Math.log(2));
        int f = pre[i][log];
        int s = pre[j - (1 << log) + 1][log];
        return Math.max(f, s);
    }

    static void fill(int [] arr, int [][] pre){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            pre[i][0] = arr[i];
        }
        for (int j = 1; j < pre[0].length; j++) {
            for (int i = 0; i < n; i++) {
                pre[i][j] = pre[i][j-1];
                if(i + (1 << (j-1)) < n){
                    pre[i][j] = Math.max(pre[i][j], pre[i + (1 << (j-1))][j-1]);
                }
            }
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

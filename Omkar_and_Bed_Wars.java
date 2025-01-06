import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            String s = in.nextLine();
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if(s.charAt(i) != s.charAt(0)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println((n+2)/3);
                continue;
            }
            if(s.charAt(0) == s.charAt(n-1)){
                int j = n-2;
                while (j > 0 && s.charAt(j) == s.charAt(n-1)) j--;
                int i = 1;
                while (i <= j && s.charAt(i) == s.charAt(0)) i++;
                int ans = (n - (j-i+1))/3;
                ans += find(s, i, j);
                System.out.println(ans);
            } else{
                System.out.println(find(s, 0, n-1));
            }
        }
    }

    static int find(String s, int i, int j){
        int ans = 0;
        int cnt = 0; char prev = '.';
        while (i <= j){
            if(s.charAt(i) != prev){
                ans += cnt/3;
                cnt = 1;
                prev = s.charAt(i);
            } else cnt++;
            i++;
        }
        ans += cnt/3;
        return ans;
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

import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            int k = in.nextInt();
            String s = in.nextLine();

            boolean hom = true;
            for (int i = 0; i < n; i++) {
                if(s.charAt(i) != s.charAt(0)){
                    hom = false;
                    break;
                }
            }
            if(hom){
                if(s.length() == k){
                    System.out.println(n);
                    continue;
                }
                System.out.println(-1);
                continue;
            }
            if(isKProper(s, 0, k)){
                System.out.println(n);
                continue;
            }

            int i = 0, cnt = 0;
            char prev = s.charAt(i);
            while (i < s.length()){
                if(s.charAt(i) == prev){
                    cnt++;
                } else{
                    if(cnt == k){
                        cnt = 1;
                        prev = s.charAt(i);
                    } else {
                        if(cnt > k){
                            i -= k;
                        }
                        break;
                    }
                }
                i++;
            }

            if(isKProper(s, i, k)){
                System.out.println(i);
                continue;
            }
            System.out.println(-1);
        }
    }

    static boolean isKProper(String s, int i, int k){
        int cnt = k;
        char prev = '.';
        int j = i-1;

        for (int l = 0; l < s.length(); l++) {
            int cur = i < s.length() ? i : j;
            if(s.charAt(cur) == prev){
                cnt++;
                if(cnt > k) return false;
            } else{
                if(cnt != k) return false;
                cnt = 1;
                prev = s.charAt(cur);
            }
            if(i < s.length()) i++;
            else j--;
        }

        return cnt == k;
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

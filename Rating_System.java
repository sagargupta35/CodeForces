import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int [] arr = ai(n);
            long [] suf = new long[n+1];
            for (int i = n - 1; i >= 0; i--) {
                suf[i] = suf[i+1] + arr[i];
            }
            for (int i = n - 1; i >=0; i--) {
                suf[i] = Math.max(suf[i], suf[i+1]);
            }
            long pre = 0, res = 0, max = suf[0];
            for (int i = 0; i < n; i++) {
                pre += arr[i];
                long cur = pre + suf[i+1];
                if(cur > max){
                    max = cur;
                    res = pre;
                }
            }
            System.out.println(res);
        }
    }

    static class Dsu{

        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        public Dsu(int n) {
            for (int i = 0; i < n; i++) {
                size.add(1);
                parent.add(i);
            }
        }

        int findPar(int u){
            if(parent.get(u) == u) return u;
            int par = findPar(parent.get(u));
            parent.set(u, par);
            return par;
        }

        void union(int u, int v){
            int up = findPar(u);
            int vp = findPar(v);
            if(up == vp) return;
            if(size.get(up) < size.get(vp)){
                parent.set(up, vp);
                size.set(vp, size.get(up) + size.get(vp));
            } else{
                parent.set(vp, up);
                size.set(up, size.get(up) + size.get(vp));
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

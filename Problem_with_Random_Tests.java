import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        StringBuilder sbr = new StringBuilder(in.nextLine());
        if(sbr.indexOf("1") == -1){
            System.out.println(0);
            return;
        }
        sbr = new StringBuilder(sbr.substring(sbr.indexOf("1")));
        int z = sbr.indexOf("0");
        if(z == -1){
            System.out.println(sbr);
            return;
        }
        n = sbr.length();
        boolean [] arr = new boolean[n];
        for (int i = 0; i < z; i++) {
            update(sbr, arr, z, i);
        }
        for (int i = 0; i < n; i++) {
            if(arr[i]){
                sbr.setCharAt(i, '1');
            }
        }
        System.out.println(sbr);
    }

    static void update(StringBuilder sbr, boolean [] arr, int z, int st){
        boolean flag = false;
        while (z < sbr.length()){
            if(sbr.charAt(z) == '0'){
                if(flag){
                    arr[z] = sbr.charAt(st) != '0';
                } else{
                    if(sbr.charAt(st) == '0'){
                        if(arr[z]) return;
                    } else{
                        if(!arr[z]){
                            flag = true;
                            arr[z] = true;
                        }
                    }
                }
            }
            z++;
            st++;
        }
    }

    static List<Pair<Integer, Integer>>[] getTree(int n) {
        List<Pair<Integer, Integer>>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            adj[u].add(new Pair<>(v, w));
            adj[v].add(new Pair<>(u, w));
        }
        return adj;
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

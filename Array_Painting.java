import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    static class Condition{
        int a;
        int b;
        int d;
        int idx;

        public Condition(int a, int b, int d, int idx) {
            this.a = a;
            this.b = b;
            this.d = d;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            Long [] pos = new Long[n+1];
            boolean [] vis = new boolean[m];
            Condition [] cons = new Condition[m];
            Map<Integer, List<Condition>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                cons[i] = new Condition(in.nextInt(), in.nextInt(), in.nextInt(), i);
                map.putIfAbsent(cons[i].a, new ArrayList<>());
                map.putIfAbsent(cons[i].b, new ArrayList<>());
                map.get(cons[i].a).add(cons[i]);
                map.get(cons[i].b).add(cons[i]);
            }
            boolean flag = true;
            for (int i = 0; i < m && flag; i++) {
                if(!vis[i]){
                    pos[cons[i].a] = 0L;
                    flag = bfs(pos, vis, cons[i].a, map);
                }
            }
            if(flag){
                System.out.println("YES");
                continue;
            }
            System.out.println("NO");
        }
    }

    static boolean bfs(Long [] pos, boolean [] vis, int st, Map<Integer, List<Condition>> map){
        Queue<Integer> q = new LinkedList<>();
        q.add(st);
        while (!q.isEmpty()){
            int j = q.poll();
            for(var con : map.get(j)){
                if(!vis[con.idx]) {
                    if (pos[con.a] == null) {
                        pos[con.a] = pos[con.b] - con.d;
                        q.add(con.a);
                    } else if (pos[con.b] == null) {
                        pos[con.b] = pos[con.a] + con.d;
                        q.add(con.b);
                    } else {
                        if (pos[con.b] - pos[con.a] != con.d) return false;
                    }
                    vis[con.idx] = true;
                }
            }
        }
        return true;
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

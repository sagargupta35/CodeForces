import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int [] sieve = new int [3 * (int)1e5 + 10];
        sieve[1] = 1;
        for (int i = 2; i < sieve.length; i++) {
            if(sieve[i] == 0){
                sieve[i] = i;
                if((long)i*i < sieve.length){
                    for (int j = i * i; j < sieve.length; j+=i) {
                        if(sieve[j] == 0){
                            sieve[j] = i;
                        }
                    }
                }
            }
        }
        int n = in.nextInt();
        int[] arr = ai(n);
        int s = in.nextInt()-1;
        int t = in.nextInt()-1;
        Set<Integer> found = new HashSet<>();
        Map<Integer, List<Integer>> map = getMap(sieve, arr);
        Queue<Integer> q = new LinkedList<>();
        int [] par = new int[n];
        Arrays.fill(par, -1);
        par[s] = -2;
        q.add(s);
        while (!q.isEmpty()){
            int j = q.poll();
            int cur = arr[j];
            while (sieve[cur] != 1){
                if(found.contains(sieve[cur])){
                    cur /= sieve[cur];
                    continue;
                }
                found.add(sieve[cur]);
                var list = map.get(sieve[cur]);
                for(int v : list){
                    if(par[v] == -1){
                        par[v] = j;
                        q.add(v);
                    }
                }
                cur /= sieve[cur];
            }
        }
        if(par[t] == -1){
            System.out.println(-1);
            return;
        }
        List<Integer> res = new ArrayList<>(n);
        res.add(t);
        while (par[t] >= 0){
            res.add(par[t]);
            t = par[t];
        }
        System.out.println(res.size());
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.print((res.get(i) + 1)+ " ");
        }
        System.out.println();
    }

    static Map<Integer, List<Integer>> getMap(int [] sieve, int [] arr){
        Map<Integer, List<Integer>> res = new HashMap<>();
        int n = arr.length;
        for(int i = 0; i<n; i++){
            int cur = arr[i];
            while (sieve[cur] != 1){
                res.putIfAbsent(sieve[cur], new ArrayList<>());
                var list = res.get(sieve[cur]);
                if(list.isEmpty() || list.get(list.size()-1) != i){
                    list.add(i);
                }
                cur /= sieve[cur];
            }
        }
        return res;
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
